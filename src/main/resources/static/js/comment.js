
document.addEventListener("DOMContentLoaded", ()=> {

    const commentText = document.querySelector('#commentText');

    //글자수 처리하는 이벤트
    commentText.addEventListener('keyup', event => { // textArea 글자수 처리
        bytes = getTextByte(commentText.value);
        document.querySelector('#commentByte').innerText = bytes;
    });

    document.querySelector('#commentSubmit').addEventListener('click', (event) => {
        if(!checkTextLenAndAlert(commentText, '댓글 내용', 100)){
            return;
        }

        let data = {
            boardidx : document.querySelector('#boardIdx').value,
            content: commentText.value
        };

        axios.post('/comment/'+document.querySelector('#lastCommentIdx').value, data)
            .then(function (response){
                console.log(response);
                let obj = response.data;
                appendCommentRow(obj);

                // 입력창 text 지우기
                commentText.value = '';

            })
            .catch(function(error) {
                console.log(error);
                alert('댓글 입력 중 오류가 발생했습니다.');
            });
    });

    document.querySelector('#nextComment').addEventListener('click', (e) => {
        let boardidx = document.querySelector('#boardIdx').value;
        let lastIdx = document.querySelector('#lastCommentIdx').value;
        axios('/comment/'+boardidx+'/'+lastIdx)
            .then(function(response){
                let obj = response.data;
                appendCommentRow(obj);
            });
    });

    const btn_edit = document.querySelector("#btn_edit");

    editModal = new bootstrap.Modal('#commentEditModal');

    btn_edit.addEventListener('click', editComment);


});



function deleteComment(idx){
    if(!confirm('댓글을 삭제하시겠습니까?\r\n삭제한 댓글은 복구할 수 없습니다.')) return;
    axios.delete('/comment/'+idx)
        .then(function(response) {
            console.log(response)
            if(response.data > 0) document.querySelector('#row-'+idx).remove();
        })
        .catch(function (error){
            console.log(error);
            alert("삭제에 실패하였습니다.");
        });
}


// 수정창 띄우기
function showEditModal(idx){
    axios.get('/comment/'+idx)
        .then(function (response){
            if(response.data != null && response.data != '') {
                // 모달에서 보여주는 show 함수 = read
                editModal.show();

                // 저장된 data get
                document.querySelector('#recommentidx').value = response.data.commentidx;
                document.querySelector('#recontent').value = response.data.content;
                document.querySelector('#commentName').value = response.data.nickname;
            } else {
                alert('본인만 수정할 수 있습니다.');
            }
        })
        .catch(function (error){
            console.log(error);
        })
}

function editComment(){

    const editdata = {
        boardidx: document.querySelector('#boardIdx').value,
        commentidx : document.querySelector('#recommentidx').value,
        content : document.querySelector('#recontent').value
    }


    axios.post('/comment/'+editdata.boardidx+'/'+editdata.commentidx, editdata)
        .then(function (response){
            console.log(" edit response >>> " + response);
            console.log(response.data)
            if(response.data > 0) {
                document.querySelector('#row-' + editdata.commentidx).children[0].children[1].children[0].textContent = editdata.content;

                editModal.hide();
            }

        })
        .catch(function (error){
            console.log(error);
        })

}

function makeCommentRow(nickname, content, writedate, commentUserIdx, commentidx, ismine = false) {
    let editBtn = '<input type="button" value="수정" class="auto-width edit-btn btn btn-outline-secondary btn-sm" onclick="showEditModal('+commentidx+')"/>';
    let deleteBtn = '<input type="button" value="삭제" class="auto-width delete-btn btn btn-outline-danger btn-sm" onclick="deleteComment('+commentidx+')"/>';
    let appendTag = '<div class="cmt col-sm-12 p-3" id="row-'+commentidx+'">'
        +'	<div class="comment-row row">'
        +'		<div class="col-md-8">'
        +'			<strong class="auto-width">'+nickname+'</strong>'
        +'			<small class="auto-width ">'+writedate+'</small>';
    appendTag += ismine ? editBtn : '';
    appendTag += ismine ? deleteBtn : '';
    appendTag += '		</div>'
        +'		<div class="col-md-8">'
        +'			<p class="card-text" >'+content+'</p>'
        +'		</div>'
        +'	</div>'
        +'</div>';
    return appendTag;
}

function appendCommentRow(obj) {
    if(obj.length == 0) {
        // 안보이게 처리
        document.querySelector('#nextComment').style.visibility = 'hidden';
    } else {
        // 여기서 append 시켜줘야 함
        document.querySelector('#nextComment').style.visibility = 'visible';
        obj.forEach((e) => {
            console.log(e);
            let appendHtml = '';
            let div = document.createElement('div');
            div.setAttribute('class', 'row');
            div.setAttribute('id', 'row-'+e.commentidx);
            appendHtml += makeCommentRow(e.nickname, e.content, e.writedate, e.useridx, e.commentidx, e.ismine);
            div.innerHTML = appendHtml;
            document.querySelector('#commentListDiv').appendChild(div);
        });
        document.querySelector('#lastCommentIdx').value = obj[obj.length-1].commentidx;

    }
}