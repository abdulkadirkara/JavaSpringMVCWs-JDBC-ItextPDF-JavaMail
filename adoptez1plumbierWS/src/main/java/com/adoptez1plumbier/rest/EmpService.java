package com.adoptez1plumbier.rest;
//package com.adoptez1plumbier.rest;
//
//import java.util.List;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.adoptez1plumbier.beans.Entity;
//import com.adoptez1plumbier.dao.PlumbierDAO;
//
//@Path("/emp")
//public class EmpService {
//
//	@Autowired
//	private PlumbierDAO empDao;
//	
//	@Path("/getAll")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAll() {
//		List<Entity> emps = empDao.plumbierFindAll();
//		return Response.status(200).entity(emps).build();
//	}
//	
//	@Path("/get/{id}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getById(@PathParam("id") int id) {
//		Entity emp = empDao.plumbierFindById(id); 
//		return Response.status(200).entity(emp).build();
//	}
//	
//	@Path("/save")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response save(List<Entity> emp) {
//		
//		for(int i=0; i<emp.size();i++) {
//		empDao.plumbierSave(emp.get(i));
////		Entity em = empDao.plumbierFindById(emp.get(i).getId());
////		empDao.plumbierSavePdf(empDao.plumbierPdfExtractor(em));
//		}
//		return Response.status(200).build();
//	}
//	
//	@Path("/update")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response update(Entity emp) {
//		empDao.plumbierUpdate(emp);
//		return Response.status(200).build();
//	}
//	
//	@Path("/delete/{id}")
//	@PUT
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response deleteById(@PathParam("id") int id) {
//		empDao.plumbierDelete(id);
//		return Response.status(200).build();
//	}
//}
