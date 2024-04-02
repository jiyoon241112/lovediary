const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    height: `500px`,
    initialEditType: 'wysiwyg',
    previewStyle: 'vertical',
    toolbarItems: [
        ['heading', 'bold', 'italic', 'strike'],
        ['hr', 'ul', 'ol'],
        ['image']
    ]
});

$(".toastui-editor-mode-switch").remove();

function getEditorContent() {
    return $(editor.wwEditor.el).children().html();
}