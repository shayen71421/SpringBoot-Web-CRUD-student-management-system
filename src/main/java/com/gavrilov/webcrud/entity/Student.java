package com.gavrilov.webcrud.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.sql.Timestamp;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private String studentId;
    @Column(name = "sr_no", unique = true, nullable = false)
    private String srNo;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "religion")
    private String religion;
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    @Column(name = "mother_tongue")
    private String motherTongue;
    @Column(name = "admission_no")
    private String admissionNo;
    @Column(name = "university_reg_no")
    private String universityRegNo;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "caste")
    private String caste;
    @Column(name = "blood_group")
    private String bloodGroup;
    @Column(name = "aadhaar_no")
    private String aadhaarNo;
    @Column(name = "abc_id")
    private String abcId;
    @Column(name = "mark1")
    private String mark1;
    @Column(name = "mark2")
    private String mark2;
    @Column(name = "academic_year")
    private String academicYear;
    @Column(name = "admitted_category")
    private String admittedCategory;
    @Column(name = "fee_concession")
    private String feeConcession;
    @Column(name = "entrance_rank")
    private String entranceRank;
    @Column(name = "nativity")
    private String nativity;
    @Column(name = "community")
    private String community;
    @Column(name = "date_of_admission")
    private LocalDate dateOfAdmission;
    @Column(name = "admission_quota")
    private String admissionQuota;
    @Column(name = "admission_type")
    private String admissionType;
    @Column(name = "eligible_category")
    private String eligibleCategory;
    @Column(name = "entrance_roll_no")
    private String entranceRollNo;
    @Column(name = "nata_score")
    private String nataScore;
    @Column(name = "let_roll_no")
    private String letRollNo;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "mother_name")
    private String motherName;
    @Column(name = "father_mobile")
    private String fatherMobile;
    @Column(name = "mother_mobile")
    private String motherMobile;
    @Column(name = "email")
    private String email;
    @Column(name = "father_occupation")
    private String fatherOccupation;
    @Column(name = "mother_occupation")
    private String motherOccupation;
    @Column(name = "phone_office")
    private String phoneOffice;
    @Column(name = "annual_income")
    private String annualIncome;
    @Column(name = "house_name")
    private String houseName;
    @Column(name = "street")
    private String street;
    @Column(name = "street2")
    private String street2;
    @Column(name = "district")
    private String district;
    @Column(name = "pin")
    private String pin;
    @Column(name = "state")
    private String state;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "branch")
    private String branch;
    @Column(name = "account_no")
    private String accountNo;
    @Column(name = "ifsc_code")
    private String ifscCode;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "parent_user_name")
    private String parentUserName;
    @Column(name = "last_login")
    private Timestamp lastLogin;
    @Column(name = "default_password")
    private String defaultPassword;
    @Column(name = "parent_default_pwd")
    private String parentDefaultPwd;
    @Column(name = "department")
    private String department;
    @Column(name = "opt_out", nullable = false)
    private Boolean optOut = false;
    @Column(name = "contributed_ig", length = 45)
    private String contributedIg;
    @Column(name = "contributor")
    private String contributor;
    @Column(name = "first_name", length = 255)
    private String firstName;
    @Column(name = "last_name", length = 255)
    private String lastName;

    public Student() {
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSrNo() {
        return srNo;
    }

    public void setSrNo(String srNo) {
        this.srNo = srNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getMotherTongue() {
        return motherTongue;
    }

    public void setMotherTongue(String motherTongue) {
        this.motherTongue = motherTongue;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getUniversityRegNo() {
        return universityRegNo;
    }

    public void setUniversityRegNo(String universityRegNo) {
        this.universityRegNo = universityRegNo;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getAbcId() {
        return abcId;
    }

    public void setAbcId(String abcId) {
        this.abcId = abcId;
    }

    public String getMark1() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1;
    }

    public String getMark2() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getAdmittedCategory() {
        return admittedCategory;
    }

    public void setAdmittedCategory(String admittedCategory) {
        this.admittedCategory = admittedCategory;
    }

    public String getFeeConcession() {
        return feeConcession;
    }

    public void setFeeConcession(String feeConcession) {
        this.feeConcession = feeConcession;
    }

    public String getEntranceRank() {
        return entranceRank;
    }

    public void setEntranceRank(String entranceRank) {
        this.entranceRank = entranceRank;
    }

    public String getNativity() {
        return nativity;
    }

    public void setNativity(String nativity) {
        this.nativity = nativity;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDate dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getAdmissionQuota() {
        return admissionQuota;
    }

    public void setAdmissionQuota(String admissionQuota) {
        this.admissionQuota = admissionQuota;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public String getEligibleCategory() {
        return eligibleCategory;
    }

    public void setEligibleCategory(String eligibleCategory) {
        this.eligibleCategory = eligibleCategory;
    }

    public String getEntranceRollNo() {
        return entranceRollNo;
    }

    public void setEntranceRollNo(String entranceRollNo) {
        this.entranceRollNo = entranceRollNo;
    }

    public String getNataScore() {
        return nataScore;
    }

    public void setNataScore(String nataScore) {
        this.nataScore = nataScore;
    }

    public String getLetRollNo() {
        return letRollNo;
    }

    public void setLetRollNo(String letRollNo) {
        this.letRollNo = letRollNo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherMobile() {
        return fatherMobile;
    }

    public void setFatherMobile(String fatherMobile) {
        this.fatherMobile = fatherMobile;
    }

    public String getMotherMobile() {
        return motherMobile;
    }

    public void setMotherMobile(String motherMobile) {
        this.motherMobile = motherMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getPhoneOffice() {
        return phoneOffice;
    }

    public void setPhoneOffice(String phoneOffice) {
        this.phoneOffice = phoneOffice;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getParentUserName() {
        return parentUserName;
    }

    public void setParentUserName(String parentUserName) {
        this.parentUserName = parentUserName;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public String getParentDefaultPwd() {
        return parentDefaultPwd;
    }

    public void setParentDefaultPwd(String parentDefaultPwd) {
        this.parentDefaultPwd = parentDefaultPwd;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getOptOut() {
        return optOut;
    }

    public void setOptOut(Boolean optOut) {
        this.optOut = optOut;
    }

    public String getContributedIg() {
        return contributedIg;
    }

    public void setContributedIg(String contributedIg) {
        this.contributedIg = contributedIg;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
