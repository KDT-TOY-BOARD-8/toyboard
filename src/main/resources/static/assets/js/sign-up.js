var usernameOverlapCheck = 0;
var password1Check = 0;
var emailOverlapCheck = 0;
var nicknameOverlapCheck = 0;

function usernameCheck() {
  const username = $("#username").val();
  if (username == "") {
    alert("아이디를 입력해주세요!. 필수항목입니다.");
    $("#username").focus();
    return false;
  }
  $.ajax({
    type: "get",
    url: "/sign-up/" + username + "/usernameExists",
    data: {"username": username},
    dataType: "json",

    success: function (result) {
      if (result.result == 0) {
        if (confirm("이 아이디는 사용 가능합니다. \n사용하시겠습니까?")) {
          usernameOverlapCheck = 1;
          $("#username").attr("readonly", true);
          $("#usernameOverlay").attr("disabled", true);
          $("#usernameOverlay").css("display", "none");
          $("#resetUsername").attr("disabled", false);
          $("#resetUsername").css("display", "inline-block");
        }
        return false;
      } else if (result.result == 1) {
        alert("이미 사용중인 아이디입니다.");
        $("#username").focus();
      } else {
        alert("success이지만 result 값이 undefined 잘못됨");
      }
    },
    error: function (request, status, error) {
      alert("code:" + request.status + "\n" + "error :" + error);
    }
  });
}

function reUsername() {
  usernameOverlapCheck = 0
  // 입력 필드 초기화
  $("#username").val("");
  // 다시입력 버튼 비활성화
  $("#resetUsername").attr("disabled", true);
  $("#resetUsername").css("display", "none");

  $("#usernameOverlay").attr("disabled", false);
  $("#usernameOverlay").css("display", "inline-block");

  $("#username").attr("readonly", false);
}

// 비밀번호 입력 필드 또는 비밀번호 재입력 필드에서 값이 변경될 때 호출하는 함수

function checkPasswordMatch() {
  var pwd1 = $("#password").val();
  var pwd2 = $("#password2").val();
  if (pwd1 == "") {
    alert("비밀번호를 입력해주세요!. 필수항목입니다.");
    $("#password").focus();
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

$("#password2").on("keyup", function () {
  checkPasswordMatch();
});

function emailCheck() {
  const email = $("#email").val();
  if (email == "") {
    alert("이메일을 입력해주세요!. 필수항목입니다.");
    $("#email").focus();
    return false;
  }
  $.ajax({
    type: "get",
    url: "/sign-up/" + email + "/emailExists",
    data: {"email": email},
    dataType: "json",

    success: function (result) {
      if (result.result == 0) {
        if (confirm("이 이메일은 사용 가능합니다. \n사용하시겠습니까?")) {
          emailOverlapCheck = 1;
          $("#email").attr("readonly", true);
          $("#emailOverlay").attr("disabled", true);
          $("#emailOverlay").css("display", "none");
          $("#resetEmail").attr("disabled", false);
          $("#resetEmail").css("display", "inline-block");
        }
        return false;
      } else if (true) {
        //   여기에 이메일 조건식 넣기
      } else if (result.result == 1) {
        alert("이미 사용중인 이메일입니다.");
        $("#email").focus();
      } else {
        alert("success이지만 result 값이 undefined 잘못됨");
      }
    },
    error: function (request, status, error) {
      alert("code:" + request.status + "\n" + "error :" + error);
    }
  });
}

function reEmail() {
  emailOverlapCheck = 0;
  // 입력 필드 초기화
  $("#email").val("");
  // 다시입력 버튼 비활성화
  $("#resetEmail").attr("disabled", true);
  $("#resetEmail").css("display", "none");

  $("#emailOverlay").attr("disabled", false);
  $("#emailOverlay").css("display", "inline-block");

  $("#email").attr("readonly", false);
}

function nicknameCheck() {
  const nickname = $("#nickname").val();
  if (nickname == "") {
    alert("닉네임을 입력해주세요!. 필수항목입니다.");
    $("#nickname").focus();
    return false;
  }
  $.ajax({
    type: "get",
    url: "/sign-up/" + nickname + "/nicknameExists",
    data: {"nickname": nickname},
    dataType: "json",

    success: function (result) {
      if (result.result == 0) {
        if (confirm("이 닉네임은 사용 가능합니다. \n사용하시겠습니까?")) {
          nicknameOverlapCheck = 1;
          $("#nickname").attr("readonly", true);
          $("#nicknameOverlay").attr("disabled", true);
          $("#nicknameOverlay").css("display", "none");
          $("#resetNickname").attr("disabled", false);
          $("#resetNickname").css("display", "inline-block");
        }
        return false;
      } else if (true) {
        //   여기에 이메일 조건식 넣기
      } else if (result.result == 1) {
        alert("이미 사용중인 닉네임입니다.");
        $("#nickname").focus();
      } else {
        alert("success이지만 result 값이 undefined 잘못됨");
      }
    },
    error: function (request, status, error) {
      alert("code:" + request.status + "\n" + "error :" + error);
    }
  });
}

function reNickname() {
  nicknameOverlapCheck = 0;
  // 입력 필드 초기화
  $("#nickname").val("");
  // 다시입력 버튼 비활성화
  $("#resetNickname").attr("disabled", true);
  $("#resetNickname").css("display", "none");

  $("#nicknameOverlay").attr("disabled", false);
  $("#nicknameOverlay").css("display", "inline-block");

  $("#nickname").attr("readonly", false);
}

function signUp1() {
  if (usernameOverlapCheck == 0) {
    alert("아이디 중복체크를 해주세요!");
    $("#username").focus();
    return false;
  } else if (password1Check == 0) {
    alert("비밀번호를 일치시켜 주세요!");
    $("#password").focus();
    $("#password2").focus();
    return false;
  } else if (emailOverlapCheck == 0) {
    alert("이메일 중복체크를 해주세요!");
    $("#email").focus();
    return false;
  } else if (nicknameOverlapCheck == 0) {
    alert("닉네임 중복체크를 해주세요!");
    $("#nickname").focus();
    return false;
  } else {
    $.ajax({
      type: "post",
      url: "/sign-up",
      data: {
        "username": $("#username").val(),
        "password": $("#password").val(),
        "email": $("#email").val(),
        "nickname": $("#nickname").val()
      },
      dataType: "text"
    }).done(function (result) {
      alert($("#username").val() + "회원가입 성공");
      location.href = "/";
    }).fail(function (error) {
      alert(JSON.stringify(error));
      // alert("code:" + request.status + "\n" + "error :" + error);
    });
  }
}
