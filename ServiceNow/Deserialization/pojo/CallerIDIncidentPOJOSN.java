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



public class CallerIDIncidentPOJOSN {
	private String link;
	private String value;
	
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
