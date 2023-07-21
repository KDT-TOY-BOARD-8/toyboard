
var password1Check = 0;
$("#newPw2").on("keyup", function () {
  checkPasswordMatch();
});

function checkPasswordMatch() {
  var pwd1 = $("#newPw1").val();
  var pwd2 = $("#newPw2").val();
  if (pwd1 == "") {
    alert("비밀번호를 입력해주세요!. 필수항목입니다.");
    $("#newPw1").focus();
    return false;
  } else if (pwd1 === pwd2) {
    password1Check = 1;

    $("#alert-success").show();
    $("#alert-danger").hide();
  } else {
    password1Check = 0;
    $("#alert-success").hide();
    $("#alert-danger").show();
  }
}


function editPw() {
  if ($("#currentPw").val().length == 0) {
    alert("현재 사용중인 비밀번호를 입력해주세요.");
    return false;
  } else if (password1Check == 0) {
    alert("비밀번호를 일치시켜 주세요!");
    $("#newPw1").focus();
    $("#newPw2").focus();
    return false;
  } else {
    $.ajax({
      type: "put",
      url: "/user/editPw",
      data: {
        "currentPw": $("#currentPw").val(),
        "toBePw": $("#newPw1").val()
      },
      dataType: "text"
    }).done(function (result) {
      alert("비밀번호 수정 성공");
      location.href = "/logout";
    }).fail(function (error) {
      alert(JSON.stringify(error));
      // alert("code:" + request.status + "\n" + "error :" + error);
    });
  }

}