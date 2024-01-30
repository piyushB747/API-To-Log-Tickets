package net.unopoint.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="apicreateticketdetails_actd")
public class EntityAPICreateTicketDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="typeid_actd")
	private Long typeIdActd;
	
	@Column(name="incidentid_actd")
	private String incidentIdActd;
	
	@Column(name="title_actd")
	private String titleActd;
	
	@Column(name="inctype_itm_actd")
	private int incTypeActd;
	
	@Column(name="category_catm_actd")
	private int categoryActd;
	
	@Column(name="subcategory_actd")
	private int subCategoryActd;
	
	@Column(name="branchcategory_actd")
	private int branchCategoryActd;
	
	@Column(name="description_actd",length = 1000)
	private String descriptionActd;

	@UpdateTimestamp
	@Column(name="changedatetime_actd")
	private LocalDateTime changeDateActd;
	
	@Column(name="changeby_actd")
	private String changeByActd;
	
	@Column(name="customer_cm_actd")
	private String customerCmActd;
	
	@Column(name="panelid_actd")
	private String panelIdActd;
	
	
	
}
