<!-- saved from url=(0041)http://10.72.203.51:8080/rtiUI/rtiUI.html -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"><HTML><HEAD>
<META content="IE=Edge" http-equiv="X-UA-Compatible">
<META name="description" content="Maestro">
<META name="keywords" content="my, my company">
<META content="no-cache" http-equiv="Cache-Control">
<META content="must-revalidate" http-equiv="Cache-Control">
<META content="no-store" http-equiv="Cache-Control">
<META content="0" http-equiv="Expires">
<META content="no-cache" http-equiv="Pragma">
<META content="text/html; charset=utf-8" http-equiv="Content-Type"><TITLE>RTI 
UI</TITLE><!--<link id="jquery_theme_link" rel="stylesheet" href="/care/struts/themes/smoothness/jquery-ui.css?s2j=3.5.0" type="text/css"/>--><LINK 
rel="shortcut icon" type="image/x-icon" href="images/favicon.ico"><LINK rel="stylesheet" 
type="text/css" href="web/css/common.css" media="Screen, projection"><!--JAVASCRIPTS-->
<SCRIPT type="text/javascript" src=""></SCRIPT>

<META name="GENERATOR" content="MSHTML 9.00.8112.16672">
<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.8.9/jquery.timepicker.css" rel="stylesheet">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

      <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
      <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.8.9/jquery.timepicker.js"></script>
	  
	  <style>
		.ui-datepicker {
			background: none;
    		border: 0px solid blank;
    		padding: 0;
		}	
		.ui-state-default,
		.ui-widget-content .ui-state-default,
		.ui-widget-header .ui-state-default {
    	border: solid none;
    	border-width: 1px 0 0 1px;
}
			
	  </style>
  
	  
      <!-- Javascript -->
      <script>
         $(function() {
            $( "#ssdatepicker" ).datepicker({ dateFormat: 'dd-mm-yy' });
            $( "#tdatepicker" ).datepicker({ dateFormat: 'dd-mm-yy' });
            
            $('#sstimepicker').timepicker();
            $('#ttimepicker').timepicker();
            
            $('#submitrunsp').on('click', function (){
            	
            	if( $('.proc').val() == 'Please select...') {
            		alert("please select the stored procedure");
            		return;
            	}
            	
            	if( $('.env').val()  == 'Please select...') {
            		alert("please select the environment");
            		return;
            	}
            	
            	var snapshotdt = $("#ssdatepicker").val();
            	var snapshottime = $("#sstimepicker").val();
            	var testdt = $("#tdatepicker").val();
            	var testtime = $("#ttimepicker").val();
            	
            	if( snapshotdt  == '') {
            		alert("please enter snapshot date");
            		return;
            	}
            	
            	if( snapshottime  == '') {
            		alert("please enter snapshot time");
            		return;
            	}

            	if( testdt  == '') {
            		alert("please enter test date");
            		return;
            	}

            	if( testtime  == '') {
            		alert("please enter test time");
            		return;
            	}
           	
            	
            	var selectedproc = $(".proc option:selected").val();
            	var selectedenv = $(".env option:selected").val();
            	
                alert("Selected stored procedure " + selectedproc +" has been executed on the environment "+ selectedenv + 
                		"\n\n Snapshot date: " + snapshotdt + " Snapshot time: " + snapshottime + "\n\n Test date: "  + testdt + " Test time: "  + testtime
                		);
            });
         });
      </script>
</HEAD>
<BODY>
<DIV id="wrap">
<DIV id="header">
<H1><IMG alt="My Company" src="web/images/logo.gif"></H1>
<H2>RTI UI</H2>
<H3>v 1.0</H3></DIV><!--/ #header -->
<DIV style="border: 2px solid grey;">
<H4>Load MDM Product Data</H4>
<TABLE>
  <TBODY>
  <TR width="50%">
    <TD>Stored Procedure</TD>
    <TD><SELECT class="proc"><OPTION>Please select...</OPTION><OPTION>SOME_PKG1.SP1</OPTION><OPTION>SOME_PKG1.SP2</OPTION><OPTION>SOME_PKG2.SP1</OPTION></SELECT></TD></TR>
  <TR width="50%">
    <TD>Target Environment</TD>
    <TD><SELECT class="env"><OPTION>Please select...</OPTION><OPTION>RTI1T</OPTION><OPTION>RTI2T</OPTION><OPTION>RTI1U</OPTION></SELECT></TD></TR>
  <TR width="50%">
    <TD>Snapshot Date</TD>
    <TD>
    <input type="text" id="ssdatepicker"/> Time: <input type="text" id="sstimepicker"/>
	</TD></TR>
      
  <TR width="50%">
    <TD>Test Date</TD>
    <TD>
    <input type="text" id="tdatepicker"/> Time: <input type="text" id="ttimepicker"/>
    </TD></TR>
  <TR>
    <TD colSpan="2"><INPUT value="Run Stored Procedure" type="button" id="submitrunsp"></TD></TR></TBODY></TABLE></DIV>
</DIV></BODY></HTML>
