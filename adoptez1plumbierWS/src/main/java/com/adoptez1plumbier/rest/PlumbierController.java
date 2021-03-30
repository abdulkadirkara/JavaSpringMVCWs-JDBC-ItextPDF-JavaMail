package com.adoptez1plumbier.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.adoptez1plumbier.beans.Entity;
import com.adoptez1plumbier.beans.PdfData;
import com.adoptez1plumbier.beans.Section;
import com.adoptez1plumbier.beans.SectionData;
import com.adoptez1plumbier.beans.User;
import com.adoptez1plumbier.dao.PlumbierDAO;
import com.adoptez1plumbier.dto.Token;
import com.adoptez1plumbier.dto.UserData;
import com.adoptez1plumbier.util.CreatePdfUtil;
import com.adoptez1plumbier.util.ErrorType;
import com.adoptez1plumbier.util.ServiceResponse;
import com.adoptez1plumbier.util.StringUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping({ "/plumbier" })
public class PlumbierController {
	@Autowired
	PlumbierDAO plumbierDao;

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = { "/save" }, method = { RequestMethod.POST })
	public ResponseEntity<ServiceResponse<Entity>> insertPlumbier(@RequestBody String stringDatas) {
		ResponseEntity<ServiceResponse<Entity>> response = null;
		ServiceResponse<Entity> returnData = new ServiceResponse<Entity>();
//		 plumbierDao.requestSaveTable(stringDatas);
		CreatePdfUtil createPdfUtil = new CreatePdfUtil();
		PdfData pdfData = new PdfData();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		Entity data = new Entity();
		String stringData = StringUtil.getStringFile(stringDatas);
		plumbierDao.requestSaveTable(stringData);
		try {
			data = objectMapper.readValue(stringData, Entity.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Section> sections = null;
		try {
			List<SectionData> sectiondatas = null;
			List<Section> sectionss = data.getSections();
			SectionData sectionData = new SectionData();
			for (int k = 0; k < sectionss.size(); k++) {
				Section section = plumbierDao.findSection(sectionss.get(k).getSectionType());
				if (section == null) {
					plumbierDao.sectionSave(sectionss.get(k));
				}
			}

			sections = plumbierDao.getSection();
			pdfData.setUid(plumbierDao.findById(data.getUserId()));
			User user = new User();

			user = plumbierDao.findById(data.getUserId());
			pdfData.setName(user.getName() + "_" + data.getUserId());
			List<PdfData> pdfwork = new ArrayList<PdfData>();
			pdfwork = plumbierDao.findByUidPDF(data.getUserId());
			PdfData pdfw;
			long workid = 1;
			if (pdfwork != null) {
				pdfw = pdfwork.get(pdfwork.size() - 1);
				if (pdfw.getWorkId() > 0) {
					for (int i = 0; i < pdfwork.size(); i++) {
						if (pdfwork.get(i).getWorkId() == pdfw.getWorkId()) {
							workid++;
							pdfData.setWorkId(workid);
						} else
							workid++;
					}
				} else {
					pdfData.setWorkId(workid);
				}
			} else {
				pdfData.setWorkId(workid);
			}
			pdfData.setPdfid(UUID.randomUUID().toString());
			pdfData.setPdf(IOUtils.toByteArray(createPdfUtil.createPdf(data, workid)));
			pdfData.setSignature(sectionss.get(3).getSignatureBaseData());
			plumbierDao.plumbierPDFSave(pdfData);
			List<PdfData> pdf = plumbierDao.findAllPDF();
			PdfData pdfdata = pdf.get(pdf.size() - 1);

			sectionData.setSid(pdfdata.getId());

			if (!data.equals(null)) {
				for (int j = 0; j < data.getSections().size(); j++) {
					if (data.getSections().size() > 0) {
						sectiondatas = data.getSections().get(j).getSectionData();
						if (data.getSections().get(j).getSectionType() == sections.get(j).getSectionType()) {
							if (sectiondatas != null) {
								if (sectiondatas.size() > 0) {
									for (int d = 0; d < sectiondatas.size(); d++) {
										sectionData.setSid(pdfdata.getId());
										sectionData.setSectionId(data.getSections().get(j).getSectionType());
										sectionData.setUserId(plumbierDao.findById(data.getUserId()));
										sectionData.setFieldName(sectiondatas.get(d).getFieldName());
										sectionData.setFieldInput(sectiondatas.get(d).getFieldInput());
										sectionData.setFieldNumberFirstSection(
												sectiondatas.get(d).getFieldNumberFirstSection());
										sectionData.setFieldIsNumberSecondSection(
												sectiondatas.get(d).getIsFieldIsNumberSecondSection());
										sectionData.setFieldsImageSecondSection(
												sectiondatas.get(d).getFieldsImageSecondSection());
										sectionData.setFieldsImageSecondSection(
												sectiondatas.get(d).getFieldsImageSecondSection());
										sectionData.setFieldsFeeling(sectiondatas.get(d).getFieldsFeeling());
										sectionData.setFieldsImageSecondSection(
												sectiondatas.get(d).getFieldsImageSecondSection());
										plumbierDao.plumbiersave(sectionData);
									}
								}
							} else
								sectiondatas = null;

						}

					}
				}
				returnData.setMessage(ErrorType.SUCCESS.getMessage());
				response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.OK);

			} else {
				returnData.setMessage(ErrorType.FAIL.getMessage());
				response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
			}

		} catch (

		Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@RequestMapping(value = { "/deleteJob" }, method = { RequestMethod.GET })
	public ResponseEntity<ServiceResponse<Entity>> deleteJob(@RequestParam("userId") long uid,
			@RequestParam("workId") long workId, @RequestParam("bossId") long bossId) {
		ResponseEntity<ServiceResponse<Entity>> response = null;
		ServiceResponse<Entity> returnData = new ServiceResponse<Entity>();
		try {
			User user = plumbierDao.findByUserBoss(bossId);
			if (user.getIsAdmin() != 0) {
				PdfData pdf = plumbierDao.downloadFindById(uid, workId);
				if (pdf != null) {
					Boolean deletedPdf = plumbierDao.deletePdf(uid, workId);
					Boolean deleted = plumbierDao.plumbierDelete(pdf.getId());
					if (deletedPdf) {
						if (deleted) {
							returnData.setMessage(ErrorType.SUCCESS.getMessage());
							response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.OK);
						} else
							System.out.println(" Data silme başarısız");
					} else {
						returnData.setMessage(ErrorType.FAIL.getMessage());
						System.out.println("PDf silme başarısız");
						response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
					}
				} else {
					returnData.setMessage(ErrorType.FAIL.getMessage());
					System.out.println("pdf yok");
					response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
				}

			} else {
				returnData.setMessage(ErrorType.FAIL.getMessage());
				System.out.println("Kullanıcı Patron Değil");
				response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/removeUser" }, method = { RequestMethod.GET })
	public ResponseEntity<ServiceResponse<Entity>> deleteUser(@RequestParam("userId") long uid,
			@RequestParam("bossId") long bossId) {
		ResponseEntity<ServiceResponse<Entity>> response = null;
		ServiceResponse<Entity> returnData = new ServiceResponse<Entity>();
		User user = plumbierDao.findByUserBoss(bossId);
		User bossMu = plumbierDao.findByUserBoss(uid);
		try {
			if (user.getIsAdmin() != 0) {
				if (bossMu.getIsAdmin() != 1) {
					Boolean deletedusers = plumbierDao.deleteUser(uid);
					if (deletedusers) {
						plumbierDao.deleteUserPdf(uid);
						plumbierDao.plumbierUserData(uid);
						returnData.setMessage(ErrorType.SUCCESS.getMessage());
						response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.OK);
					} else {
						returnData.setMessage(ErrorType.FAIL.getMessage());
						System.out.println("Kullanıcı yok");
						response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
					}
				} else {
					returnData.setMessage(ErrorType.FAIL.getMessage());
					System.out.println("Patron Silinemez");
					response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
				}
			} else {
				returnData.setMessage(ErrorType.FAIL.getMessage());
				System.out.println("Kullanıcı Patron Değil");
				response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/uploadProfileImg" }, method = { RequestMethod.POST })
	public ResponseEntity<ServiceResponse<User>> uploadProfileImg(@RequestBody String users) {
		ResponseEntity<ServiceResponse<User>> response = null;
		ServiceResponse<User> returnData = new ServiceResponse<User>();
		User user = new User();
		ObjectMapper objectMapper = new ObjectMapper();
		plumbierDao.requestSaveTable(users);
		try {

			String data = StringUtil.getByteStringFile(users);
			System.out.println(data);
			user = objectMapper.readValue(data, User.class);

			plumbierDao.UploadProfileImg(user);
			returnData.setMessage(ErrorType.SUCCESS.getMessage());
			response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/getAll" }, method = { RequestMethod.GET })
	public ResponseEntity<ServiceResponse<List<SectionData>>> getAllPlumbier() {
		ResponseEntity<ServiceResponse<List<SectionData>>> response = null;
		ServiceResponse<List<SectionData>> returnData = new ServiceResponse<List<SectionData>>();
		List<SectionData> entities = null;
		try {
			entities = this.plumbierDao.tblplumbierFindAll();
			returnData.setData(entities);
			returnData.setMessage(ErrorType.SUCCESS.getMessage());
			returnData.setData(entities);
			response = new ResponseEntity<ServiceResponse<List<SectionData>>>(returnData, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<List<SectionData>>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "findById/{id}" }, method = { RequestMethod.GET })
	public ResponseEntity<ServiceResponse<SectionData>> findById(@PathVariable Long id) {
		ResponseEntity<ServiceResponse<SectionData>> response = null;
		ServiceResponse<SectionData> returnData = new ServiceResponse<SectionData>();
		try {
			SectionData entity = this.plumbierDao.plumbierFindById(id.longValue());
			if (!entity.equals(null)) {
				returnData.setData(entity);
				returnData.setMessage(ErrorType.SUCCESS.getMessage());
				response = new ResponseEntity<ServiceResponse<SectionData>>(returnData, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<SectionData>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/authentication" }, method = { RequestMethod.POST })
	public ResponseEntity<ServiceResponse<User>> authentication(@RequestBody User user) {
		ResponseEntity<ServiceResponse<User>> response = null;
		ServiceResponse<User> returnData = new ServiceResponse<User>();
		try {
			User users = this.plumbierDao.plumbierfindUser(user);
			if (!users.equals(null)) {
				returnData.setMessage(ErrorType.SUCCESS.getMessage());
				returnData.setData(users);
				response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/singUp" }, method = { RequestMethod.POST })
	public ResponseEntity<ServiceResponse<User>> SingUp(@RequestBody User user) {
		ResponseEntity<ServiceResponse<User>> response = null;
		ServiceResponse<User> returnData = new ServiceResponse<User>();
		try {
			if (!user.equals(null) || !user.getName().isEmpty()) {
				User users = this.plumbierDao.findByLoginUser(user);
				if (users == null) {
					users = this.plumbierDao.plumbierSingUp(user);
					returnData.setMessage(ErrorType.SUCCESS.getMessage());
					returnData.setData(users);
					response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.OK);
				} else {
					returnData.setMessage(ErrorType.USER_USED.getMessage());
					response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.ACCEPTED);
				}
			} else {
				returnData.setMessage(ErrorType.FAIL.getMessage());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/getUserNotBoss" }, method = { RequestMethod.GET })
	public ResponseEntity<ServiceResponse<List<User>>> getUserNotBoss() {
		ResponseEntity<ServiceResponse<List<User>>> response = null;
		ServiceResponse<List<User>> returnData = new ServiceResponse<List<User>>();
		try {
			List<User> users = plumbierDao.getAllUserNotBoss();
			if (!users.equals(null)) {
				returnData.setData(users);
				returnData.setMessage(ErrorType.SUCCESS.getMessage());
				response = new ResponseEntity<ServiceResponse<List<User>>>(returnData, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<List<User>>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/getUserfindById" }, method = { RequestMethod.GET })
	public ResponseEntity<ServiceResponse<User>> getUserfindById(@RequestParam("id") long uid) {
		ResponseEntity<ServiceResponse<User>> response = null;
		ServiceResponse<User> returnData = new ServiceResponse<User>();
		try {
			User entity = plumbierDao.findById(uid);
			if (!entity.equals(null)) {
				returnData.setData(entity);
				returnData.setMessage(ErrorType.SUCCESS.getMessage());
				response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = "/downloadPdf", method = RequestMethod.GET)
	public void downloadPdfFile(@RequestParam("uid") long uid, @RequestParam("id") long id,
			HttpServletResponse response) throws Exception {
		PdfData entity = null;
		try {
			entity = plumbierDao.downloadFindById(uid, id);

			InputStream fileIO = new ByteArrayInputStream(entity.getPdf());

			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition",
					"attachment; filename=" + entity.getName() + "_" + entity.getUid() + entity.getWorkId() + ".pdf");
			IOUtils.copy(fileIO, response.getOutputStream());
			response.flushBuffer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@RequestMapping(value = { "/findByDataUid" }, method = { RequestMethod.GET })
	public ResponseEntity<ServiceResponse<UserData>> plumbierFindByUid(@RequestParam("id") long uId) {
		ResponseEntity<ServiceResponse<UserData>> response = null;
		ServiceResponse<UserData> returnData = new ServiceResponse<UserData>();
		try {
			UserData entity = this.plumbierDao.isUidPlumbierData(uId);

			if (!entity.equals(null)) {
				returnData.setData(entity);
				returnData.setMessage(ErrorType.SUCCESS.getMessage());
				response = new ResponseEntity<ServiceResponse<UserData>>(returnData, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<UserData>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/forgotPassword" }, method = { RequestMethod.GET })
	public ResponseEntity<ServiceResponse<UserData>> forgotPassword(@RequestParam("userName") String userName) {
		ResponseEntity<ServiceResponse<UserData>> response = null;
		ServiceResponse<UserData> returnData = new ServiceResponse<UserData>();
		Token token = new Token();
		User usr = new User();
		SimpleMailMessage message = new SimpleMailMessage();
		Multipart multipart = new MimeMultipart();

		try {
			usr.setUsername(userName);
			User user = plumbierDao.findByLoginUser(usr);

			if (user != null) {
				Token tok = plumbierDao.findByTokenUser(userName);
				if (tok == null) {
					token.setToken(UUID.randomUUID().toString());
					token.setUser(usr);
					plumbierDao.userToken(token);
				} else {
					boolean result = plumbierDao.findUpdateToken(tok);
					if (result)
						tok = plumbierDao.findByTokenUser(userName);
				}
				if (tok != null) {

					String link = "http://37.148.210.196:8080/adoptez1plumbierWS/plumbier/passwordForm?token="
							+ tok.getToken();
					String text = "";

					MimeMessage messages = mailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(messages, true);
					helper.setTo(userName);
					helper.setReplyTo(userName);
					helper.setSubject("Forgot Password !");
					helper.setText(
							"<html><body><H2>Réinitialisation du mot de passe!!!</h2></br><a href=" + link
									+ ">Veuillez cliquer pour réinitialiser votre mot de passe...</a></body></html>",
							true);
					mailSender.send(messages);

				}
			} else {
				returnData.setMessage(ErrorType.FAIL.getMessage());
				response = new ResponseEntity<ServiceResponse<UserData>>(returnData, HttpStatus.BAD_REQUEST);
			}
			returnData.setMessage(ErrorType.SUCCESS.getMessage());
			response = new ResponseEntity<ServiceResponse<UserData>>(returnData, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			response = new ResponseEntity<ServiceResponse<UserData>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/passwordForm" }, method = RequestMethod.GET)
	public String handleGetRequest(@RequestParam("token") String token, Model model) {

		HttpSession session = null;
		Token result = plumbierDao.findByToken(token);
		if (result.getToken() != null) {
			model.addAttribute("token", token);
			model.addAttribute("user", result.getUser().getName());
			return "forgotPassword";
		}
		return token;

	}

	@RequestMapping(value = { "/passwordConfirm" }, method = RequestMethod.GET)
	public String passwordConfirm(Model model, HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = null;
		String pass = req.getParameter("pass");
		String token = req.getParameter("token");

		// String token = res.getHeader("token");
		Token result = plumbierDao.findByToken(token.toString());
		if (result.getToken() != null) {
			Boolean sonuc = plumbierDao.userPasswordUpdate(result.getUser().getName(), pass);
			try {
				if (sonuc) {
					session = req.getSession(true);

					return "success";
				} else
					return "error";
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			return "error";
		return token.toString();
	}

}
