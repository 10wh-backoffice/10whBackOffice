// let like = document.querySelector(".like");

let heartEl = document.getElementById("heart");
heartEl.addEventListener("click", function() {
  console.log("clicked!")
  heartEl.classList.toggle("active");
  
});
const urlParams = new URL(location.href).searchParams;

const id = urlParams.get('id');
const url = `http://localhost:8080/api/posts/${id}`;
sendAjax(url);
function sendAjax(url) {
  const oReq = new XMLHttpRequest();

  oReq.addEventListener("load", function() {
    console.log(oReq.responseText);
    makeTemplate(JSON.parse(oReq.responseText));
  });
  oReq.open("GET", url);
  oReq.send();
}
function makeTemplate(data) {
  const title = document.querySelector(".board-title");
  const content = document.querySelector(".board-content");

  title.innerText = data.title;
  content.innerText = data.content;
}
