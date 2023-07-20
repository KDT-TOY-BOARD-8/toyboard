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
  var pwd1 = $("#password1").val();
  var pwd2 = $("#password2").val();

  if (pwd1 === pwd2) {

    $("#alert-success").show();
    $("#alert-danger").hide();
  } else {
    $("#alert-success").hide();
    $("#alert-danger").show();
  }
}


$("#password2").on("keyup", function() {
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
          usernameOverlapCheck = 1;
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
  // 입력 필드 초기화
  $("#nickname").val("");
  // 다시입력 버튼 비활성화
  $("#resetNickname").attr("disabled", true);
  $("#resetNickname").css("display", "none");

  $("#nicknameOverlay").attr("disabled", false);
  $("#nicknameOverlay").css("display", "inline-block");

  $("#nickname").attr("readonly", false);
}
