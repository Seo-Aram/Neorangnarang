
var fileCnt = 0;
var fileList = [];

document.addEventListener('DOMContentLoaded', () => {
//이미지 파일 드래그 & 드롭 시키는 영역
    document.querySelector('#dropFileZone').addEventListener('drop', (event) => {
        event.preventDefault();
        ajaxFileUpload(event.dataTransfer.files);
    });

// 탐색 창 포출하는 영역
    document.querySelector('#fileExplorer').addEventListener('click', (event) => {
        document.querySelector('#selectFile').click();
        document.querySelector('#selectFile').onchange = function () {
            ajaxFileUpload(document.querySelector('#selectFile').files);
        };
    });
});

// 파일 업로드 작업
function ajaxFileUpload(files_obj) {
    if (files_obj != undefined) {
        if (files_obj.length + fileCnt > 5) {
            alert("파일은 최대 5개 까지 업로드 가능합니다.");
            return;
        }
        let uploadFileData = new FormData();
        for (i = 0; i < files_obj.length; i++) {
            let fileNames = files_obj[i].name.split('.');
            if (fileNames[fileNames.length - 1].toUpperCase() != 'PNG'
                && fileNames[fileNames.length - 1].toUpperCase() != 'JPG'
                && fileNames[fileNames.length - 1].toUpperCase() != 'GIF') {
                alert('이미지 파일만 업로드 가능합니다. 다시 확인해주세요.')
                return;
            }
            uploadFileData.append('file' + i, files_obj[i]);
        }
        axios.post('/board/upload', uploadFileData)
            .then(function (response) {
                let opt = '';
                obj = response.data;
                console.log(obj.fileList)
                obj.fileList.forEach(e => fileList.push(e));
                fileList.forEach(e => {
                    opt += '<option value="' + e + '">' + e + '</option>';
                });
                document.querySelector('#thumbnail').value = obj.thumbnail[0];

                if (opt != '') {
                    fileCnt += files_obj.length;
                    document.querySelector('#fileList').innerHTML = opt;
                    document.querySelector('#fileCnt').innerText = fileCnt;
                }
            })
            .catch(function (error) {
                alert("Error!!!\r\n occurred when trying to upload your file.\r\n" + error);
            });
    }
}