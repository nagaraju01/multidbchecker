$(function () {


    // handles form submit
    $(document).on('submit', '#callProcedureForm', sendCallProcedureForm);

    function sendCallProcedureForm() {
        return saveForm($('#callProcedureForm'), "/callProcedure");
    }

    function saveForm($form, url) {

        var jqxhr,
            data = toKeyValArray($form.serializeArray());
        jqxhr = doPost(getBasePath() + url, data);
        jqxhr.done(function (res) {
            if (!res.hasErrors) {
                onSendCallProcedureFormSuccess($form, data);
            }
        }).fail(function (res) {
            alert('An error occurred.');
        }).always(function (res) {
        	//
        });
        return false;
    }

    function onSendCallProcedureFormSuccess($form, data) {
        //
    }
    
    function getBasePath(){
    	return $('#basepath').text();
    }
});


function toKeyValArray(a) {
    var d = {};
    for (i in a) {
        var n = a[i].name.split(".");
        if (n.length === 2) {
            if (typeof d[n[0]] === 'undefined') {
                d[n[0]] = {};
            }
            d[n[0]][n[1]] = a[i].value;
        } else {
            d[a[i].name] = a[i].value;
        }
    }
    return d;
}

function doPost(url, data) {
    return jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': url,
        'data': JSON.stringify(data),
        'dataType': 'json'
    });
}

