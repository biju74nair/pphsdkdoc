var currentPage = 'gettingstarted';
var sdkversion="v16";
var deviceType;


function goto(path,file){

    if(file === "") file = "index.html";

    $.ajax({url: path+"/"+file, success: function(result){
        $("#content").html(result);
        highlight();
        enableClipboard();
        swipebox();
    }});
    
}

function version(version){
    this.version = version;
}

function deviceType(deviceType){
    this.deviceType = deviceType;
}

function highlight() {
    var snippets = document.getElementsByTagName('code');
    Array.prototype.forEach.call(snippets, function(snippet) {
        hljs.highlightBlock(snippet);
    });
  }

  function enableClipboard(){
    var clipboard = new Clipboard('.copy-button',{
    });
    clipboard.on('success', function(e) {
        e.clearSelection();
    });
  }

  function swipebox(){
    // $( '.swipebox' ).swipebox();
   // $('.swipebox-video').swipebox();
  }

  function grabAccessToken(){
    $.getJSON({url: "/services/accesstoken", success: function(result){
        var credentials = document.getElementById('credential');
        var code = credentials.innerHTML;
        code = code.replace("&lt;access_token&gt;",result.access_token);
        credentials.innerHTML = code;
    }});
  }

function registerDevices(){
    var optradio = $("input[name=optradio]:checked").val();
    if(optradio === 'no'){
        $('#register').hide();
        $('#no').show();
    } else {
        $('#no').hide();
        $('#register').show();
    }
}

function showAuthOptions() {
    var authradio = $("input[name=authradio]:checked").val();
    if(authradio === 'test'){
        $('#actual').hide();
        $('#test').show();
    } else {
        $('#test').hide();
        $('#actual').show();
    }
}

function toggleMenu(id){
    var obj = document.getElementById(id);
    if(obj.style.display === 'none'){
        obj.style.display = '';
    } else {
        obj.style.display = 'none';
    }
}