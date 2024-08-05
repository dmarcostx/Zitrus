const activationEvent = event => {
    event.preventDefault();
    const t = event.target;
    const id = t.attributes.getNamedItem("rule-id").value;
    if (t.className === 'enable') {
        $.ajax({
            url: '?id=' + id + '&active=true',
            type: 'PUT',
            success: () => {
                $('#active-' + id).text('Sim');
                t.className = 'disable';
                t.text = 'Desativar';
            }
        });
    } else {
        $.ajax({
            url: '?id=' + id + '&active=false',
            type: 'PUT',
            success: () => {
                $('#active-' + id).text('Não');
                t.className = 'enable';
                t.text = 'Ativar';
            }
        });
    }
}
$(document).ready(() => {
    $('.disable').click(activationEvent);
    $('.enable').click(activationEvent);
})