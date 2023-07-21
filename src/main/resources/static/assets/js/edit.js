var emailOverlapCheck = 0;
var nicknameOverlapCheck = 0;

window.onload = () => {
  getMyInfo();
}


function getMyInfo() {
  $.ajax({
    type: "get",
    url : "/user/edit2",
    dataType: "json",
    async : false,
    success : function (response) {
      if (response.nickname.length==0 && response.email.length==0) {
        alert("조회된 데이터가 없습니다.")
        return false;
      }

      // 렌더링 할 html을 저장할 변수
      let myInfoHtml = '';

      // 정보 html 추가
      myInfoHtml +=`
      <div class="row justify-content-evenly">

                <div class="col-lg-2">
                  <label for="email">E-mail</label>
                </div>

                <div class="col-lg-5 mb-3 justify-content-center" id="email_label">
                  <input type="email" class="form-control" id="email" name="email" value=${response.email}>
                  <p></p>
                  <input class="btn btn-outline-primary btn-sm idCheck mx-1" type="button" id="emailOverlay"
                         onclick="emailCheck()" value="중복 체크"/>
                  <input class="btn btn-outline-success btn-sm reType" type="button" id="resetEmail"
                         onclick="reEmail()" disabled value="다시입력"/>
                </div>

              </div>
              
              <div class="row justify-content-evenly">

                <div class="col-lg-2">
                  <label for="nickname">Nickname</label>
                </div>

                <div class="col-lg-5 mb-3 justify-content-center">
                  <input type="text" class="form-control" id="nickname" name="nickname" value=${response.nickname}>
                  <p></p>
                  <input class="btn btn-outline-primary btn-sm idCheck mx-1" type="button" id="nicknameOverlay"
                         onclick="nicknameCheck()" value="중복 체크"/>
                  <input class="btn btn-outline-success btn-sm reType" type="button" id="resetNickname"
                         onclick="reNickname()" disabled value="다시입력"/>
                </div>

              </div>
      `;

      document.querySelector(".info_data").innerHTML = myInfoHtml;

    },
    error : function (request, status, error) {
      console.log(error)
    }
  })
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


function editMyInfo() {
  if (emailOverlapCheck == 0) {
    alert("이메일 중복체크를 해주세요!");
    $("#email").focus();
    return false;
  } else if (nicknameOverlapCheck == 0) {
    alert("닉네임 중복체크를 해주세요!");
    $("#nickname").focus();
    return false;
  } else {
    $.ajax({
      type: "put",
      url: "/user/edit",
      data: {
        "email": $("#email").val(),
        "nickname": $("#nickname").val()
      },
      dataType: "text"
    }).done(function (result) {
      alert("회원정보 수정 성공");
      location.href = "/user/my-info";
    }).fail(function (error) {
      alert(JSON.stringify(error));
      // alert("code:" + request.status + "\n" + "error :" + error);
    });
  }
}
