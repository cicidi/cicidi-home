/**
 * Created by cicidi on 2/26/2017.
 */
function getProperty(propertyName) {
    var result;
    $.ajax({
        url: getContextPath() + "property?propertyName=" + propertyName,
        dataType: 'text',
        processData: false,
        contentType: false,
        async: false,
        type: 'GET',
        success: function (data) {
            result = data;
        }
    });
    return result;
}

