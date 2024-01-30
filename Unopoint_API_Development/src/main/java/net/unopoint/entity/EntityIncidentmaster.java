package net.unopoint.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="incidentmaster")
public class EntityIncidentmaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="typeid_im")
	private Long typeIdIm;
	
	@Column(name="inctype_itm_im")
	private String incTypeItmIm;
	
	@Column(name="title_im")
	private String titleIm;
	
	@Column(name="category_catm_im")
	private int categoryCatIm;
	
	@Column(name="branchcategory_im")
	private int branchCategoryIm;
	
	@Column(name="description_im")
	private String descriptionIm;
	
	@Column(name="status_sm_im")
	private int statusSmIm;
	
	@Column(name="deleteflag_im")
	private String deleteFlagIm;
	
	@Column(name="customer_cm_im")
	private int customerCmIM;
	
	@Column(name="assetid_im")
	private String assetIdIm;
	
	@Column(name="mode_im")
	private String modeIm;
	
	@Column(name="CustomerVisit_im")
	private String customerVisitIm;
	
	@Column(name="CustNamePhone_im")
	private String customerNamePhoneIm;
	
	@Column(name="closeddate_im")
	private String closedDateIm;
	
	@Column(name="closedtime_im")
	private String closedtime_im;
	
	@Column(name="ticketclosedbyname_im")
	private String ticketClosedByNameIm;
	
	@Column(name="ticketclosedbyrole_im")
	private String ticketclosedbyrole_im;
	
	@Column(name="pmdone_im")
	private String pmDoneIm;
	
	@Column(name="etadate_im")
	private String etaDateIm;
	
	@Column(name="etadeviation_im")
	private String etaDeviationIm;
	
	@Column(name="etatimehour_im")
	private String etaTimeHourIm;
	
	@Column(name="etatimeminute_im")
	private String etaTimeMinuteIm;
	
	@Column(name="resolution_im")
	private String resolutionIm;
	
	@Column(name="sensorremark_im")
	private String sensorRemarkIm;
	
	@Column(name="troubleshooting_im")
	private String troubleShootingIm;
	
	@Column(name="subcategory_im")
	private String subCategoryIm;
	
	@Column(name="resolutiondescription_im")
	private String resolutionDescriptionIm;
	
	@Column(name="atadate_im")
	private String ataDateIm;
	
	@Column(name="atatimehour_im")
	private String ataTimehHourIm;
	
	@Column(name="atatimeminute_im")
	private String ataTimeMinuteIm;
	
	@Column(name="techclosedate_im")
	private String techCloseDateIm;
	
	@Column(name="techclosetime_im")
	private String techCloseTimeIm;

	@Column(name="urgency_urgencym_im")
	private String urgencyUrgencymIm;
	
	@Column(name="incdate_im")
	private String incdateIm;
	
	@Column(name="inctime_im")
	private String inctimeIm;
}



