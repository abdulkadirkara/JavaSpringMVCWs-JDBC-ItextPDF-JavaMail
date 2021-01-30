package com.adoptez1plumbier.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Entity implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long userId;
	private String date;
	private List<Section> sections;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

	
	
	
	
	
	//
//	private long id;
//	private Date date;
//	private String quitus;
//	private String nomDuRresponsable;
//	private String nomDuChantier;
//	private String adresseDuChantier;
//	private String etage;
//	private String porte;
//	private String locataire;
//	private String nonDesInstalateur;
//	private String ouiNom;
//	private String nombre;
//	private String rempDuCompteur;
//	private String rempDuClaper;
//	private String rempDuLaColonneEf;
//	private String rempDuLaColonneEcs;
//	private String rempDuLaChute;
//	private String rempDuLaChaudiere;
//	private String rempDuLaRadiateur;
//	private String rempDuRoai;
//	private String reseauxGazLonguer;
//	private String rempBaignoire;
//	private String rempMitigeurBaignoire;
//	private String rempDoche;
//	private String rempMitigeurDoche;
//	private String rempWcComplet;
//	private String rempLAVABO;
//	private String rempColonneLAVABO;
//	private String rempMitigeurLAVABO;
//	private String poseDeMal;
//	private String creResEfEcs;
//	private String creResEvacuation;
//	private String obsevation;
//	private String comentaireLocataire;
//	private String degrDuLogement;
//	private String propDuLogement;
//	private String respectDesDelais;
//	private String commEtInformation;
//	private String compDesOuvrier;
//	private String bien;
//	private String moyen;
//	private String mauvais;
//	private byte[] thumbnail;
//
//	public Entity() {
//		super();
//
//	}
//
//	public Entity(long id, Date date, String quitus, String nomDuRresponsable, String nomDuChantier,
//			String adresseDuChantier, String etage, String porte, String locataire, String nonDesInstalateur,
//			String ouiNom, String nombre, String rempDuCompteur, String rempDuClaper, String rempDuLaColonneEf,
//			String rempDuLaColonneEcs, String rempDuLaChute, String rempDuLaChaudiere, String rempDuLaRadiateur,
//			String rempDuRoai, String reseauxGazLonguer, String rempBaignoire, String rempMitigeurBaignoire,
//			String rempDoche, String rempMitigeurDoche, String rempWcComplet, String rempLAVABO,
//			String rempColonneLAVABO, String rempMitigeurLAVABO, String poseDeMal, String creResEfEcs,
//			String creResEvacuation, String obsevation, String comentaireLocataire,
//			String degrDuLogement, String propDuLogement, String respectDesDelais,
//			String commEtInformation, String compDesOuvrier, String bien, String moyen, String mauvais,
//			byte[] thumbnail) {
//		super();
//		this.id = id;
//		this.date = date;
//		this.quitus = quitus;
//		this.nomDuRresponsable = nomDuRresponsable;
//		this.nomDuChantier = nomDuChantier;
//		this.adresseDuChantier = adresseDuChantier;
//		this.etage = etage;
//		this.porte = porte;
//		this.locataire = locataire;
//		this.nonDesInstalateur = nonDesInstalateur;
//		this.ouiNom = ouiNom;
//		this.nombre = nombre;
//		this.rempDuCompteur = rempDuCompteur;
//		this.rempDuClaper = rempDuClaper;
//		this.rempDuLaColonneEf = rempDuLaColonneEf;
//		this.rempDuLaColonneEcs = rempDuLaColonneEcs;
//		this.rempDuLaChute = rempDuLaChute;
//		this.rempDuLaChaudiere = rempDuLaChaudiere;
//		this.rempDuLaRadiateur = rempDuLaRadiateur;
//		this.rempDuRoai = rempDuRoai;
//		this.reseauxGazLonguer = reseauxGazLonguer;
//		this.rempBaignoire = rempBaignoire;
//		this.rempMitigeurBaignoire = rempMitigeurBaignoire;
//		this.rempDoche = rempDoche;
//		this.rempMitigeurDoche = rempMitigeurDoche;
//		this.rempWcComplet = rempWcComplet;
//		this.rempLAVABO = rempLAVABO;
//		this.rempColonneLAVABO = rempColonneLAVABO;
//		this.rempMitigeurLAVABO = rempMitigeurLAVABO;
//		this.poseDeMal = poseDeMal;
//		this.creResEfEcs = creResEfEcs;
//		this.creResEvacuation = creResEvacuation;
//		this.obsevation = obsevation;
//		this.comentaireLocataire = comentaireLocataire;
//		this.degrDuLogement = degrDuLogement;
//		this.propDuLogement = propDuLogement;
//		this.respectDesDelais = respectDesDelais;
//		this.commEtInformation = commEtInformation;
//		this.compDesOuvrier = compDesOuvrier;
//		this.bien = bien;
//		this.moyen = moyen;
//		this.mauvais = mauvais;
//		this.thumbnail = thumbnail;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//	public String getQuitus() {
//		return quitus;
//	}
//
//	public void setQuitus(String quitus) {
//		this.quitus = quitus;
//	}
//
//	public String getNomDuRresponsable() {
//		return nomDuRresponsable;
//	}
//
//	public void setNomDuRresponsable(String nomDuRresponsable) {
//		this.nomDuRresponsable = nomDuRresponsable;
//	}
//
//	public String getNomDuChantier() {
//		return nomDuChantier;
//	}
//
//	public void setNomDuChantier(String nomDuChantier) {
//		this.nomDuChantier = nomDuChantier;
//	}
//
//	public String getAdresseDuChantier() {
//		return adresseDuChantier;
//	}
//
//	public void setAdresseDuChantier(String adresseDuChantier) {
//		this.adresseDuChantier = adresseDuChantier;
//	}
//
//	public String getEtage() {
//		return etage;
//	}
//
//	public void setEtage(String etage) {
//		this.etage = etage;
//	}
//
//	public String getPorte() {
//		return porte;
//	}
//
//	public void setPorte(String porte) {
//		this.porte = porte;
//	}
//
//	public String getLocataire() {
//		return locataire;
//	}
//
//	public void setLocataire(String locataire) {
//		this.locataire = locataire;
//	}
//
//	public String getNonDesInstalateur() {
//		return nonDesInstalateur;
//	}
//
//	public void setNonDesInstalateur(String nonDesInstalateur) {
//		this.nonDesInstalateur = nonDesInstalateur;
//	}
//
//	public String getOuiNom() {
//		return ouiNom;
//	}
//
//	public void setOuiNom(String ouiNom) {
//		this.ouiNom = ouiNom;
//	}
//
//	public String getNombre() {
//		return nombre;
//	}
//
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//	public String getRempDuCompteur() {
//		return rempDuCompteur;
//	}
//
//	public void setRempDuCompteur(String rempDuCompteur) {
//		this.rempDuCompteur = rempDuCompteur;
//	}
//
//	public String getRempDuClaper() {
//		return rempDuClaper;
//	}
//
//	public void setRempDuClaper(String rempDuClaper) {
//		this.rempDuClaper = rempDuClaper;
//	}
//
//	public String getRempDuLaColonneEf() {
//		return rempDuLaColonneEf;
//	}
//
//	public void setRempDuLaColonneEf(String rempDuLaColonneEf) {
//		this.rempDuLaColonneEf = rempDuLaColonneEf;
//	}
//
//	public String getRempDuLaColonneEcs() {
//		return rempDuLaColonneEcs;
//	}
//
//	public void setRempDuLaColonneEcs(String rempDuLaColonneEcs) {
//		this.rempDuLaColonneEcs = rempDuLaColonneEcs;
//	}
//
//	public String getRempDuLaChute() {
//		return rempDuLaChute;
//	}
//
//	public void setRempDuLaChute(String rempDuLaChute) {
//		this.rempDuLaChute = rempDuLaChute;
//	}
//
//	public String getRempDuLaChaudiere() {
//		return rempDuLaChaudiere;
//	}
//
//	public void setRempDuLaChaudiere(String rempDuLaChaudiere) {
//		this.rempDuLaChaudiere = rempDuLaChaudiere;
//	}
//
//	public String getRempDuLaRadiateur() {
//		return rempDuLaRadiateur;
//	}
//
//	public void setRempDuLaRadiateur(String rempDuLaRadiateur) {
//		this.rempDuLaRadiateur = rempDuLaRadiateur;
//	}
//
//	public String getRempDuRoai() {
//		return rempDuRoai;
//	}
//
//	public void setRempDuRoai(String rempDuRoai) {
//		this.rempDuRoai = rempDuRoai;
//	}
//
//	public String getReseauxGazLonguer() {
//		return reseauxGazLonguer;
//	}
//
//	public void setReseauxGazLonguer(String reseauxGazLonguer) {
//		this.reseauxGazLonguer = reseauxGazLonguer;
//	}
//
//	public String getRempBaignoire() {
//		return rempBaignoire;
//	}
//
//	public void setRempBaignoire(String rempBaignoire) {
//		this.rempBaignoire = rempBaignoire;
//	}
//
//	public String getRempMitigeurBaignoire() {
//		return rempMitigeurBaignoire;
//	}
//
//	public void setRempMitigeurBaignoire(String rempMitigeurBaignoire) {
//		this.rempMitigeurBaignoire = rempMitigeurBaignoire;
//	}
//
//	public String getRempDoche() {
//		return rempDoche;
//	}
//
//	public void setRempDoche(String rempDoche) {
//		this.rempDoche = rempDoche;
//	}
//
//	public String getRempMitigeurDoche() {
//		return rempMitigeurDoche;
//	}
//
//	public void setRempMitigeurDoche(String rempMitigeurDoche) {
//		this.rempMitigeurDoche = rempMitigeurDoche;
//	}
//
//	public String getRempWcComplet() {
//		return rempWcComplet;
//	}
//
//	public void setRempWcComplet(String rempWcComplet) {
//		this.rempWcComplet = rempWcComplet;
//	}
//
//	public String getRempLAVABO() {
//		return rempLAVABO;
//	}
//
//	public void setRempLAVABO(String rempLAVABO) {
//		this.rempLAVABO = rempLAVABO;
//	}
//
//	public String getRempColonneLAVABO() {
//		return rempColonneLAVABO;
//	}
//
//	public void setRempColonneLAVABO(String rempColonneLAVABO) {
//		this.rempColonneLAVABO = rempColonneLAVABO;
//	}
//
//	public String getRempMitigeurLAVABO() {
//		return rempMitigeurLAVABO;
//	}
//
//	public void setRempMitigeurLAVABO(String rempMitigeurLAVABO) {
//		this.rempMitigeurLAVABO = rempMitigeurLAVABO;
//	}
//
//	public String getPoseDeMal() {
//		return poseDeMal;
//	}
//
//	public void setPoseDeMal(String poseDeMal) {
//		this.poseDeMal = poseDeMal;
//	}
//
//	public String getCreResEfEcs() {
//		return creResEfEcs;
//	}
//
//	public void setCreResEfEcs(String creResEfEcs) {
//		this.creResEfEcs = creResEfEcs;
//	}
//
//	public String getCreResEvacuation() {
//		return creResEvacuation;
//	}
//
//	public void setCreResEvacuation(String creResEvacuation) {
//		this.creResEvacuation = creResEvacuation;
//	}
//
//	public String getObsevation() {
//		return obsevation;
//	}
//
//	public void setObsevation(String obsevation) {
//		this.obsevation = obsevation;
//	}
//
//	public String getComentaireLocataire() {
//		return comentaireLocataire;
//	}
//
//	public void setComentaireLocataire(String comentaireLocataire) {
//		this.comentaireLocataire = comentaireLocataire;
//	}
//
//	public String getDegrDuLogement() {
//		return degrDuLogement;
//	}
//
//	public void setDegrDuLogement(String degrDuLogement) {
//		this.degrDuLogement = degrDuLogement;
//	}
//
//	public String getPropDuLogement() {
//		return propDuLogement;
//	}
//
//	public void setPropDuLogement(String propDuLogement) {
//		this.propDuLogement = propDuLogement;
//	}
//
//	public String getRespectDesDelais() {
//		return respectDesDelais;
//	}
//
//	public void setRespectDesDelais(String respectDesDelais) {
//		this.respectDesDelais = respectDesDelais;
//	}
//
//	public String getCommEtInformation() {
//		return commEtInformation;
//	}
//
//	public void setCommEtInformation(String commEtInformation) {
//		this.commEtInformation = commEtInformation;
//	}
//
//	public String getCompDesOuvrier() {
//		return compDesOuvrier;
//	}
//
//	public void setCompDesOuvrier(String compDesOuvrier) {
//		this.compDesOuvrier = compDesOuvrier;
//	}
//
//	public String getBien() {
//		return bien;
//	}
//
//	public void setBien(String bien) {
//		this.bien = bien;
//	}
//
//	public String getMoyen() {
//		return moyen;
//	}
//
//	public void setMoyen(String moyen) {
//		this.moyen = moyen;
//	}
//
//	public String getMauvais() {
//		return mauvais;
//	}
//
//	public void setMauvais(String mauvais) {
//		this.mauvais = mauvais;
//	}
//
//	public byte[] getThumbnail() {
//		return thumbnail;
//	}
//
//	public void setThumbnail(byte[] thumbnail) {
//		this.thumbnail = thumbnail;
//	}

}
