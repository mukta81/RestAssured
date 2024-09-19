	package Deserialization.pojo;
	
	
	/*
	 * format of JSON response
	 * {
	 * "result": [
	       {
            "number": "INC0009002",
            "sys_id": "1c832706732023002728660c4cf6a7b9",
            "short_description": "My computer is not detecting the headphone device",
            "opened_at": "2018-09-16 12:49:23",
            "caller_id": {
                "link": "https://dev282303.service-now.com/api/now/table/sys_user/77ad8176731313005754660c4cf6a7de",
                "value": "77ad8176731313005754660c4cf6a7de"
           				},
            "description": "My computer is not detecting the headphone device. It could be an issue with the USB port.",
            "category": "Hardware"
        },
        {
            "number": "INC0000026",
            "sys_id": "46f1784ba9fe19810018aa27fbb23482",
            "short_description": "Seem to have an issue with my hard drive...",
            "opened_at": "2024-03-01 23:54:59",
            "caller_id": {
                "link": "https://dev282303.service-now.com/api/now/table/sys_user/5137153cc611227c000bbd1bd8cd2006",
                "value": "5137153cc611227c000bbd1bd8cd2006"
            				},
            "description": "Hard drive has been making a loud grinding noise for the last two days.",
            "category": "hardware"
        }
	 * 			]
	 * }
	 * 
	 * 
	 */

public class ResultModalIncidentPOJOSN {
	
	private String number;
	private String sys_id;
	private String short_description;
	private String opened_at;
	private CallerIDIncidentPOJOSN caller_id;
	private String description;
	private String category;
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}
	public String getShort_description() {
		return short_description;
	}
	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}
	public String getOpened_at() {
		return opened_at;
	}
	public void setOpened_at(String opened_at) {
		this.opened_at = opened_at;
	}
	public CallerIDIncidentPOJOSN getCaller_id() {
		return caller_id;
	}
	public void setCaller_id(CallerIDIncidentPOJOSN caller_id) {
		this.caller_id = caller_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
