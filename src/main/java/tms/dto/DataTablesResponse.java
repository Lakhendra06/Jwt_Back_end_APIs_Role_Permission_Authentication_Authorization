package tms.dto;

import java.util.List;

import lombok.Data;
/*
 * {
 *  "sEcho":1,
 *  "iColumns":7,
 *  "sColumns":"",
 *  "iDisplayStart":0,
 *  "iDisplayLength":10,
 *  "amDataProp":[0,1,2,3,4,5,6],
 *  "sSearch":"",
 *  "bRegex":false,
 *  "asSearch":["","","","","","",""],
 *  "abRegex":[false,false,false,false,false,false,false],
 *  "abSearchable":[true,true,true,true,true,true,true],
 *  "iSortingCols":1,
 *  "aiSortCol":[0],
 *  "asSortDir":["asc"],
 *  "abSortable":[true,true,true,true,true,true,true]
 * }
 */
@Data
public class DataTablesResponse {
    /*
	 * This parameter is changed on the client side for evry ajax call.
	 * Cast to integer to avoid Xss attacks
	 */       
	private int sEcho;

	/*
	 * Total number of records
	 */
	private int iTotalRecords;
	
	/*
	 * Total number of records after filtering. We are not filtering but datatables expects this parameter 
	 */
	private int  iTotalDisplayRecords;
	
	/*
	 * Datasource.
	 * By default, DataTables will expect a 2D array for its data source. However, Using the 'aoColumns' parameter 
	 * in the datatables call we can map properties to columns
	 * Flexjson converts the list of returned objects into a json objects array.
	 */
	private List<?> aaData;
    
    /*
	 * Property used for sending messages (errors, info, etc) from server in the response.
	 */
    //	private String serverMessage;
    //	
	//private String sColumns;

    public DataTablesResponse(int sEcho, int iTotalRecords, int iTotalDisplayRecords,
			List<?> aaData) {
		super();
		this.sEcho = sEcho;
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.aaData = aaData;
        //this.serverMessage = serverMessage;
		//this.sColumns = sColumns;
	}

    //public String toJson() {
        //return new JSONSerializer().exclude("*.class").serialize(this);
    //}
}
