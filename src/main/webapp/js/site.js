document.addEventListener('submit',(e)=>{
    const form = e.target;
    if(form.id==='signup-form')
    {
        e.preventDefault();
        let name = form.querySelector('[name="userName"]').value;
        let email = form.querySelector('[name="userEmail"]').value;
        let password = form.querySelector('[name="userPassword"]').value;
        fetch(form.action ,{
            method: 'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name,
                email,
                password
            })
        }).then(res => res.json()).then(data => {
            console.log(data);

            // Проверяем статус ответа
            if (data.status && data.status.isOk) {

                window.location.reload();
            } else {
                // Обработка ошибок, если нужно
                console.error("Error of registration: ", data.status.phrase);
            }
        })
            .catch(error => {
                // Обработка сетевых ошибок
                console.error("Error request: ", error);
            });
    }

});
document.getElementById("toggleSignUp").addEventListener("click", function () {
    var signInForm = document.getElementById("signInForm");
    var signUpForm = document.getElementById("signUpForm");
    var modalTitle = document.getElementById("signInModalLabel");
    var toggleButton = document.getElementById("toggleSignUp");

    if (signInForm.style.display === "none") {
        signInForm.style.display = "block";
        signUpForm.style.display = "none";
        modalTitle.textContent = "Sign In";
        toggleButton.textContent = "Don't have an account? Sign Up";
    } else {
        signInForm.style.display = "none";
        signUpForm.style.display = "block";
        modalTitle.textContent = "Sign Up";
        toggleButton.textContent = "Already have an account? Sign In";
    }
});
document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('signInForm');

    form.addEventListener('submit', (e) => {
        e.preventDefault(); // Предотвращаем стандартную отправку формы

        let email = form.querySelector('[name="inputUserEmail"]').value;
        let password = form.querySelector('[name="inputUserPassword"]').value;

        fetch(form.action, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email,
                password
            })
        })
            .then(response => response.json())
            .then(data => {
                if (data.status === 'ok') {
                    // Закрыть модальное окно и обновить страницу
                    const signInModal = bootstrap.Modal.getInstance(document.getElementById('signInModal'));
                    if (signInModal) {
                        signInModal.hide();
                    }
                    window.location.reload();
                } else {
                    alert('Error occured!');
                }
            })
            .catch(error => {
                console.error('Error', error);
            });
    });
});
document.addEventListener('submit', (e) => {
    const form = e.target;
    if(form.id === 'signUpForm') {
        // Обработчик для регистрации
        e.preventDefault();
        let name = form.querySelector('[name="userName"]').value;
        let email = form.querySelector('[name="userEmail"]').value;
        let password = form.querySelector('[name="userPassword"]').value;

        fetch(form.action, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name,
                email,
                password
            })
        }).then(res => res.json()).then(data => {
            console.log(data);

            // Проверяем статус ответа
            if (data.status && data.status.isOk) {
                // Закрыть модальное окно
                const signInModal = bootstrap.Modal.getInstance(document.getElementById('signInModal'));
                if (signInModal) {
                    signInModal.hide();
                }
                window.location.reload();

                window.location.reload();
            } else {
                // Обработка ошибок, если нужно
                console.error("Error of registration: ", data.status.phrase);
            }
        })
            .catch(error => {
                // Обработка сетевых ошибок
                console.error("Error request: ", error);
            });
    }
});