
/*
$(document).ready(function (){

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
            boardIdx : $('#boardIdx').val(),
            content: commentText.value
        };

        $.ajax({
            url: '/comment/'+$('#lastCommentIdx').val(),
            type: 'post',
            async : true,
            data: JSON.stringify(data),
            dataType : 'json',
            success : function (data){
                let obj = data;
                // for
                appendCommentRow(obj);
                // 마지막에 lsatidx를 val()에 넣어줌
            },
            error : function (request, status, error){

                console.log(request);
                console.log(status);
                console.log(error);
                alert('댓글 입력 중 오류가 발생했습니다.');
            }
        })
    });

    $('#nextComment').click( function (){
        let lastIdx = $('#lastCommentIdx').val();
        let boardIdx = $('#boardIdx').val();
        $.ajax( {
            url : '/comment/'+boardIdx+'/'+lastIdx,
            type: 'get',
            async : true,
            dataType : 'json',
            success : function (data){
                let obj = data;
                appendCommentRow(obj);
            },
            error : function (request, status, error) {
                console.log(error);
            }
        });
    });

});


function deleteComment(idx){
    if(!confirm('댓글을 삭제하시겠습니까?\r\n삭제한 댓글은 복구할 수 없습니다.')) return;
    $.ajax({
        url: '/comment/'+idx,
        type: 'delete',
        async : true,
        dataType : 'json',
        success : function(data){
            if(data > 0) $('#row-'+idx).remove();
        },
        error : function (request, status, error) {
            console.log(error);
        }
    })

}


function makeCommentRow(nickname, content, writedate, commentUserIdx, commentidx) {
    let deleteBtn = '<input type="button" value="삭제" class="delete-btn btn btn-outline-danger" onclick="deleteComment('+commentidx+')"/>';
    let appendTag = '<div class="col-sm-12" id="row-'+commentidx+'">'
        +'	<div class="comment-row row">'
        +'		<div class="col-md-2"></div>'
        +'		<div class="col-md-8">'
        +'			<strong>'+nickname+'</strong>'
        +'			<small>'+writedate+'</small>';
    /!*appendTag += commentUserIdx == [[${loginInfo.useridx}]] ? deleteBtn : '';*!/
    appendTag += '		</div>'
        +'		<div class="col-md-2"></div>'
        +'		<div class="col-md-2"></div>'
        +'		<div class="col-md-8">'
        +'			<p class="card-text" >'+content+'</p>'
        +'		</div>'
        +'		<div class="col-md-2"></div>'
        +'	</div>'
        +'</div>';
    return appendTag;
}


function appendCommentRow(obj) {
    if(obj.length == 0) {
        // 안보이게 처리
        $('#nextComment').css('visibility', 'hidden');
    } else {
        // 여기서 append 시켜줘야 함
        $('#nextComment').css('visibility', 'visible');

        $.each(obj, function(index, e) {
            let appendHtml = '';
            let div = document.createElement('div');
            div.setAttribute('class', 'row');
            div.setAttribute('id', 'row-'+e.commentidx);
            appendHtml += makeCommentRow(e.nickname, e.content, e.writedate, e.useridx, e.commentidx);
            div.innerHTML = appendHtml;
            document.querySelector('#commentListDiv').appendChild(div);
        });
        $('#lastCommentIdx').val(obj[obj.length-1].commentidx);
    }
}*/


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
            })
            .catch(function(error) {
                console.log(error);
                alert('댓글 입력 중 오류가 발생했습니다.');
            });
    });

    document.querySelector('#nextComment').addEventListener('click', (e) => {
        let lastIdx = document.querySelector('#lastCommentIdx').value;
        axios('/comment/'+boardidx+'}/'+lastIdx)
            .then(function(response){
                let obj = response.data;
                appendCommentRow(obj);
            });
    });

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

function makeCommentRow(nickname, content, writedate, commentUserIdx, commentidx) {
    let deleteBtn = '<input type="button" value="삭제" class="delete-btn btn btn-outline-danger" onclick="deleteComment('+commentidx+')"/>';
    let appendTag = '<div class="col-sm-12" id="row-'+commentidx+'">'
        +'	<div class="comment-row row">'
        +'		<div class="col-md-2"></div>'
        +'		<div class="col-md-8">'
        +'			<strong>'+nickname+'</strong>'
        +'			<small>'+writedate+'</small>';
    /*appendTag += commentUserIdx == ${loginInfo.useridx} ? deleteBtn : '';*/
    appendTag += deleteBtn;
    appendTag += '		</div>'
        +'		<div class="col-md-2"></div>'
        +'		<div class="col-md-2"></div>'
        +'		<div class="col-md-8">'
        +'			<p class="card-text">'+content+'</p>'
        +'		</div>'
        +'		<div class="col-md-2"></div>'
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
            appendHtml += makeCommentRow(e.nickname, e.content, e.writedate, e.useridx, e.commentidx);
            div.innerHTML = appendHtml;
            document.querySelector('#commentListDiv').appendChild(div);
        });
        document.querySelector('#lastCommentIdx').value = obj[obj.length-1].commentidx;
    }
}