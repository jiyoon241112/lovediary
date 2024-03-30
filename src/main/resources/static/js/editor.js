const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    height: `500px`,
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    toolbarItems: [
        ['heading', 'bold', 'italic', 'strike'],
        ['hr', 'ul', 'ol'],
        ['image']
    ]
});

$(".toastui-editor-mode-switch .tab-item").eq(1).click()
$(".toastui-editor-mode-switch").remove();