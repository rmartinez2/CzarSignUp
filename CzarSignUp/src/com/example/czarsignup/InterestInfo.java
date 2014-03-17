package com.example.czarsignup;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class InterestInfo {

	private String JSON_ID = "id";
	private String JSON_FN = "FirstName";
	private String JSON_LN = "LastName";
	private String JSON_EM = "Email";
	private String JSON_SY = "SchoolYear";
	private String JSON_PN = "PhoneNumber";
	private String JSON_MA = "Major";
	private String JSON_PERM = "Permission";

	private UUID mId;
	private String mFirstName;
	private String mLastName;
	private String mEmail;
	private String mSchoolYr;
	private String mPhoneNumber;
	private String mPermission;
	private String mMajor;

	public InterestInfo() {
		mId = UUID.randomUUID();
	}

	public InterestInfo(JSONObject object) throws JSONException {
		mId = UUID.fromString(object.getString(JSON_ID));
		mFirstName = object.getString(JSON_FN);
		mLastName = object.getString(JSON_LN);
		mEmail = object.getString(JSON_EM);
		mSchoolYr = object.getString(JSON_SY);
		mPhoneNumber = object.getString(JSON_PN);
		mPermission = object.getString(JSON_PERM);
		mMajor = object.getString(JSON_MA);
	}

	public String getFN() {
		return mFirstName;
	}

	public void setFN(String firstName) {
		mFirstName = firstName;
	}

	public String getLN() {
		return mLastName;
	}

	public void setLN(String lastName) {
		mLastName = lastName;
	}

	public String getEM() {
		return mEmail;
	}

	public void setEM(String email) {
		mEmail = email;
	}

	public String getSY() {
		return mSchoolYr;
	}

	public void setSY(String schoolyr) {
		mSchoolYr = schoolyr;
	}

	public UUID getId() {
		return mId;
	}

	public String getPermission() {
		return mPermission;
	}

	public void setPermission(String permission) {
		mPermission = permission;
	}

	public String getPhoneNumb() {
		return mPhoneNumber;
	}

	public void setPhoneNumb(String pn) {
		mPhoneNumber = pn;
	}

	public String getMajor() {
		return mMajor;
	}

	public void setMajor(String major) {
		mMajor = major;
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_FN, mFirstName);
		json.put(JSON_LN, mLastName);
		json.put(JSON_EM, mEmail);
		json.put(JSON_MA, mMajor);
		json.put(JSON_PERM, mPermission);
		json.put(JSON_PN, mPhoneNumber);
		json.put(JSON_SY, mSchoolYr);
		return json;
	}

}
