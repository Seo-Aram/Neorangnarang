// 글자수 확인
function getTextByte(str) {
    return ~-encodeURI(str).split(/%..|./).length;
}

function checkTextLenAndAlert(element, content, len) {
    if(element.value == '' || element.value.trim() == '' || getTextByte(element.value) > len) {
        alert(content + '을 다시 확인 해주세요.\r\n' +
            '현재 작성한 글자 수('+getTextByte(element.value)+'/' + len + ')');
        element.focus();
        return false;
    }
    return true;
}

function checkPriceAndAlert(element) {
    let check = /^[0-9]+$/;
    if(element.value == '' || element.value.trim() == '' || parseInt(element.value) <= 0) {
        alert('판매 금액은 필수입니다.');
        element.focus();
        return false;
    }
    if(!check.test(element.value)) {
        alert('숫자만 입력 가능합니다.');
        element.focus();
        return false;
    }
    return true;
}