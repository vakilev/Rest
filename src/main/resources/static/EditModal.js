const editModal = document.getElementById('ModalEdit')
editModal.addEventListener('show.bs.modal', event => {
    // Button that triggered the modal
    const button = event.relatedTarget
    // Extract info from data-bs-* attributes
    const userId = button.getAttribute('data-bs-userId')
    const userName = button.getAttribute('data-bs-userName')
    const userSurname = button.getAttribute('data-bs-userSurname')
    const userAge = button.getAttribute('data-bs-userAge')
    const userEmail = button.getAttribute('data-bs-userEmail')
    // Update the modal's content.

    const modaluserId = editModal.querySelector('#userId')
    const modaluserName = editModal.querySelector('#userName')
    const modaluserSurname = editModal.querySelector('#userSurname')
    const modaluserAge = editModal.querySelector('#userAge')
    const modaluserEmail = editModal.querySelector('#userEmail')

    modaluserId.value = userId
    modaluserName.value = userName
    modaluserSurname.value = userSurname
    modaluserAge.value = userAge
    modaluserEmail.value = userEmail


})