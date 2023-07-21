window.onload = () => {
  getMyInfo();
}


function getMyInfo() {
  $.ajax({
    type: "get",
    url : "user/edit2",
    dataType: "json",
    async : false,
    success : function (response) {
      if (!response.length) {
        alert("조회된 데이터가 없습니다.")
        return false;
      }

      // 렌더링 할 html을 저장할 변수
      let myInfoHtml = '';

      // 정보 html 추가
      alert(response.nickname);
      alert(response.email);
      alert("sss");
      console.log(response.nickname);
      console.log(response.email);


    },
    error : function (request, status, error) {
      console.log(error)
    }
  })
}