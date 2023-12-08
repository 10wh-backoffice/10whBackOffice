document.addEventListener("DOMContentLoaded", function () {

});

let heartEl = document.getElementById("heart");
heartEl.addEventListener("click", function () {
    console.log("clicked!")
    heartEl.classList.toggle("active");

});
const urlParams = new URL(location.href).searchParams;

const postId = urlParams.get('id');
const postGetUrl = `http://localhost:8080/api/posts/${postId}`;
const commentGetUrl = `http://localhost:8080/api/posts/${postId}/comments`;
sendAjax(postGetUrl);
axios.get(commentGetUrl).then(function (response) {
    let data = response.data.responseBody.data;

    makeCommentTemplate(data);
});

function sendAjax(url) {
    const oReq = new XMLHttpRequest();

    oReq.addEventListener("load", function () {
        console.log(oReq.responseText);
        makeTemplate(JSON.parse(oReq.responseText));
    });
    oReq.open("GET", url);
    oReq.send();
}

function makeTemplate(data) {
    const title = document.querySelector(".board-title");
    const content = document.querySelector(".board-content");

    title.innerText = data.responseBody.data.title;
    content.innerText = data.responseBody.data.content;
}

// async function sendComment() {
//   const commentPostUrl = `http://localhost:8080/api/posts/${postId}comments`;
//   const commentContent = document.querySelector(".comment-content").value();
//   const data = {comment : commentContent};
//   const response = await axios.post(commentPostUrl, data);
//   return response.data
// }

document.querySelector(".btn").addEventListener("click", function () {
    const commentPostUrl = `http://localhost:8080/api/posts/${postId}/comments`;
    const commentGetUrl = `http://localhost:8080/api/posts/${postId}/comments`;
    let commentContent = document.querySelector(".comment-input .comment-content");
    let data = {content: commentContent.value};
    commentContent.value="";

    axios.post(commentPostUrl, data).then(function () {
        alert("댓글이 등록되었습니다");
        axios.get(commentGetUrl).then(function (response) {
            let data = response.data.responseBody.data;
            makeCommentTemplate(data);
        });
    }).catch(e => console.error(e));
}, false);

function makeCommentTemplate(data) {
    let template = document.getElementById("comment-template").innerText;

    let bindTemplate = Handlebars.compile(template);

    let resultHtml = data.reduce(function (prve, next) {
        return prve + bindTemplate(next);
    }, "");
    let body = document.querySelector(".comment-each-container");
    body.innerHTML = resultHtml;
}