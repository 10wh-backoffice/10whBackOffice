function sendAjax(url) {
  const oReq = new XMLHttpRequest();
  // oReq.onreadystatechange()
}

document.addEventListener("DOMContentLoaded", function() {

});

function PageLoader() {

}
PageLoader.prototype.onLoadPage = function() {


}
// let template = document.getElementById("ticket_box").innerText;
// let bindTemplate = Handlebars.compile(template);

// let innerHtml = data.reduce(function (prve, next) {
//     next.price = Number(next.price).toLocaleString();
//     return prve + bindTemplate(next);
// }, "");

// let ticket_body = document.querySelector(".ticket_body");
// ticket_body.innerHTML = innerHtml;

let data = [
  {
  "boardId" : "1",
  "title" : "test1",
  "createdAt": "2022.22.22"
},
{
  "boardId" : "2",
  "title" : "test2",
  "createdAt": "2022.22.22"
},
{
  "boardId" : "3",
  "title" : "test3",
  "createdAt": "2022.22.22"
}

]


makeTemplate(data);

function makeTemplate(data) {
  console.log(data);
  let template = document.getElementById("content-template").innerText;

  let bindTemplate = Handlebars.compile(template);

  let resultHtml = data.reduce(function(prve, next) {
    return prve + bindTemplate(next);
  }, "");
  let body = document.querySelector(".content-section .inner");
  body.innerHTML = resultHtml;

}