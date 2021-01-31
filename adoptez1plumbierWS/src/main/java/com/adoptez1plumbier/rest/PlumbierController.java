package com.adoptez1plumbier.rest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.adoptez1plumbier.dto.UserData;
import com.adoptez1plumbier.util.CreatePdfUtil;
import com.adoptez1plumbier.util.ErrorType;
import com.adoptez1plumbier.util.ServiceResponse;

@Controller
@RequestMapping({ "/plumbier" })
public class PlumbierController {
	@Autowired
	PlumbierDAO plumbierDao;

	@RequestMapping(value = { "/save" }, method = { RequestMethod.POST })
	public ResponseEntity<ServiceResponse<Entity>> insertPlumbier(@RequestBody Entity data) {
		ResponseEntity<ServiceResponse<Entity>> response = null;
		ServiceResponse<Entity> returnData = new ServiceResponse<Entity>();
		CreatePdfUtil createPdfUtil = new CreatePdfUtil();
		PdfData pdfData = new PdfData();
		List<Section> sections = null;
		try {
			List<SectionData> sectiondatas = null;
			sections = plumbierDao.getSection();
			SectionData sectionData = new SectionData();
			
			pdfData.setUid(plumbierDao.findById(data.getUserId()));
			User user = new User();
			user = plumbierDao.findById(data.getUserId());
			pdfData.setName(user.getName() + "_" + data.getUserId());
			pdfData.setPdf(IOUtils.toByteArray(createPdfUtil.createPdf(data)));
			plumbierDao.plumbierPDFSave(pdfData);
			List<PdfData> pdf= plumbierDao.findAllPDF();
			PdfData pdfdata = pdf.get(pdf.size()-1);
			sectionData.setSid(pdfdata.getId());
			
			if (!data.equals(null)) {
				for (int j = 0; j < data.getSections().size(); j++) {
					if (data.getSections().size() > 0) {
						sectiondatas = data.getSections().get(j).getSectionData();
						if (data.getSections().get(j).getSectionType() == sections.get(j).getSectionType()) {
							if (sectiondatas.size() > 0) {
								for (int d = 0; d < sectiondatas.size(); d++) {
									sectionData.setSid(pdfdata.getId());
									sectionData.setSectionId(data.getSections().get(j).getSectionType());
									sectionData.setUserId(plumbierDao.findById(data.getUserId()));
									sectionData.setFieldName(sectiondatas.get(d).getFieldName());
									sectionData.setFieldInput(sectiondatas.get(d).getFieldInput());
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

						}

					}
				}

				returnData.setMessage(ErrorType.SUCCESS.getMessage());
				returnData.setMessage(ErrorType.SUCCESS.getCode());
				returnData.setData(data);
				response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.OK);

			} else {
				returnData.setMessage(ErrorType.ERROR.getMessage());
				returnData.setCode(ErrorType.ERROR.getCode());
				response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
			}

		} catch (

		Exception ex) {
			ex.printStackTrace();
			returnData.setData(data);
			returnData.setMessage(ErrorType.ERROR.getMessage());
			returnData.setMessage(ErrorType.ERROR.getCode());
			response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@RequestMapping(value = { "/delete/{id}" }, method = { RequestMethod.POST })
	public ResponseEntity<ServiceResponse<Entity>> deletePlumbier(@PathVariable long id) {
		ResponseEntity<ServiceResponse<Entity>> response = null;
		ServiceResponse<Entity> returnData = new ServiceResponse<Entity>();
		try {
			this.plumbierDao.plumbierDelete(id);
			returnData.setMessage(ErrorType.SUCCESS.getMessage());
			returnData.setMessage(ErrorType.SUCCESS.getCode());
			response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.ERROR.getMessage());
			returnData.setMessage(ErrorType.ERROR.getCode());
			response = new ResponseEntity<ServiceResponse<Entity>>(returnData, HttpStatus.BAD_REQUEST);
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
			returnData.setMessage(ErrorType.SUCCESS.getCode());
			returnData.setData(entities);
			response = new ResponseEntity<ServiceResponse<List<SectionData>>>(returnData, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.ERROR.getMessage());
			returnData.setMessage(ErrorType.ERROR.getCode());
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
				returnData.setMessage(ErrorType.SUCCESS.getCode());
				response = new ResponseEntity<ServiceResponse<SectionData>>(returnData, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.ERROR.getMessage());
			returnData.setMessage(ErrorType.ERROR.getCode());
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
				returnData.setCode(ErrorType.SUCCESS.getCode());
				returnData.setData(users);
				response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			returnData.setCode(ErrorType.FAIL.getCode());
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
					returnData.setCode(ErrorType.SUCCESS.getCode());
					returnData.setData(users);
					response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.OK);
				} else {
					returnData.setMessage(ErrorType.USER_USED.getMessage());
					returnData.setCode(ErrorType.USER_USED.getCode());
					response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.ACCEPTED);
				}
			} else {
				returnData.setMessage(ErrorType.FAIL.getMessage());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.ERROR.getMessage());
			returnData.setCode(ErrorType.ERROR.getCode());
			response = new ResponseEntity<ServiceResponse<User>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/getUserNotBoss" }, method = { RequestMethod.GET })
	public ResponseEntity<ServiceResponse<List<User>>> getUserNotBoss() {
		ResponseEntity<ServiceResponse<List<User>>> response = null;
		ServiceResponse<List<User>> returnData = new ServiceResponse<List<User>>();
		try {
			List<User> users = this.plumbierDao.getAllUserNotBoss();
			if (!users.equals(null)) {
				returnData.setMessage(ErrorType.SUCCESS.getMessage());
				returnData.setCode(ErrorType.SUCCESS.getCode());
				returnData.setData(users);
				response = new ResponseEntity<ServiceResponse<List<User>>>(returnData, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			returnData.setCode(ErrorType.FAIL.getCode());
			response = new ResponseEntity<ServiceResponse<List<User>>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = { "/getUserfindById" }, method = { RequestMethod.GET })
	public ResponseEntity<ServiceResponse<UserData>> getUserfindById(@RequestParam("id") long uid) {
		ResponseEntity<ServiceResponse<UserData>> response = null;
		ServiceResponse<UserData> returnData = new ServiceResponse<UserData>();
		try {
			UserData entity = this.plumbierDao.plumbierFindAllUserData(uid);
			if (!entity.equals(null)) {
				returnData.setMessage(ErrorType.SUCCESS.getMessage());
				returnData.setCode(ErrorType.SUCCESS.getCode());
				returnData.setData(entity);
				response = new ResponseEntity<ServiceResponse<UserData>>(returnData, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.FAIL.getMessage());
			returnData.setCode(ErrorType.FAIL.getCode());
			response = new ResponseEntity<ServiceResponse<UserData>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@RequestMapping(value = "/downloadPdf", method = RequestMethod.GET)
	public void downloadPdfFile(@RequestParam("id") Long id, HttpServletResponse response) throws Exception {

		PdfData entity = null;

		try {
			entity = plumbierDao.downloadFindById(id);

			InputStream fileIO = new ByteArrayInputStream(entity.getPdf());

			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition",
					"attachment; filename=" + entity.getName() + "_" + entity.getId() + ".pdf");
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
			UserData entity = this.plumbierDao.plumbierFindAllUserData(uId);

			if (!entity.equals(null)) {
				returnData.setData(entity);
				returnData.setMessage(ErrorType.SUCCESS.getMessage());
				returnData.setMessage(ErrorType.SUCCESS.getCode());
				response = new ResponseEntity<ServiceResponse<UserData>>(returnData, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			returnData.setMessage(ErrorType.ERROR.getMessage());
			returnData.setMessage(ErrorType.ERROR.getCode());
			response = new ResponseEntity<ServiceResponse<UserData>>(returnData, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

}
