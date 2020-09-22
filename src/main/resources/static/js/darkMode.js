const checkbox = document.getElementById("checkbox");

checkbox.addEventListener("change", () => {
    document.body.classList.toggle('dark');
    let button = document.getElementById('button');
    button.classList.toggle('btn-primary');
    button.classList.toggle('btn-secondary');
});