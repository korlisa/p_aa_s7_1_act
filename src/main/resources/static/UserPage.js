/*
Скрипт заполняет таблицу все поля в navbar и таблицу About User для /admin и /user
 */
$(async function () {
    await loadUser();
});

async function loadUser() {
    fetch("http://localhost:8888/api/user")
        .then(r => r.json()) //читаем ответ в JSON
        .then(data => {
            let passenger = `$(
            <tr>
                <td>${data.passenger.firstName}</td>
                <td>${data.passenger.lastName}</td>
                <td>${data.passenger.middleName}</td>
                <td>${data.passenger.dateOfBirth}</td>`;
            //Вставляем все в тело таблицы
            $('#personalData').append(passenger);

            let user = `$(
            <tr>
                <td>${data.email}</td>
                <td>${data.passenger.phoneNumber}</td>`;
            //Вставляем все в тело таблицы
            $('#personalContactsTable').append(user);
        })
        //Выводим ошибку если что-то пошло не так
        .catch((error) => {
            alert(error);
        });
}




