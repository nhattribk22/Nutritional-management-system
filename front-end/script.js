// BMI tool
const submitBtn = document.querySelector(`.submitBtn`);
const height = document.querySelector(`.height`);
const weight = document.querySelector(`.weight`);
const result = document.querySelector(`.output`);
const output = document.querySelector(`.display-4`);
const message = document.querySelector(`.message`);
const hidden = document.querySelector(`.hidden`);
const str = `, here are our advises`;
submitBtn.addEventListener("click", function () {
  const h = height.value / 100;
  const w = weight.value;
  if (h && w) {
    let res;
    res = w / (h * h);
    res = Math.round(res * 10) / 10;
    hidden.classList.remove("hidden");
    result.textContent = res;
    if (res <= 18.5) message.textContent = `You are Underweight${str}`;
    else if (res < 25) message.textContent = `You are Normal weight${str}`;
    else if (res < 30) message.textContent = `You are Overweight${str}`;
    else message.textContent = `You are Obesity${str}`;
    console.log(message);
  }
});

// Calories tool

// Search raw
let keyWords = [
  `Beef`,
  `pork`,
  "hello World",
  "hello Nam",
  "hi1",
  "hi2",
  "hi3",
  "hi4",
];

const resultBox = document.querySelector(`.result-box`);
const inputBox = document.querySelector(`#input-box`);

inputBox.onkeyup = function () {
  let result = [];
  let input = inputBox.value;
  if (input.length) {
    result = keyWords.filter((kWord) => {
      return kWord.toLowerCase().includes(input.toLowerCase());
    });
    console.log(result, typeof result);
  }
  display(result);

  if (!result.length) {
    resultBox.innerHTML = ``;
  }
};

function display(result) {
  const textContent = result.map((list) => {
    return `<li onclick=selectInput(this)> ${list} </li>`;
  });
  resultBox.innerHTML = "<ul>" + textContent.join("") + "</ul>";
}

function selectInput(list) {
  inputBox.value = list.innerHTML;
  resultBox.innerHTML = ``;
}

// Show material
const numOutput = document.querySelectorAll(`.material`);
const calo = document.querySelector(`.calory`);
const carb = document.querySelector(`.carb`);
const protein = document.querySelector(`.protein`);
const fat = document.querySelector(`.fat`);
const searchBtn = document.querySelector(`.searchBtn`);

let material = {
  calories: 1800,
  carb: 20,
  fat: 10220,
  protein: 230,
};

searchBtn.addEventListener("click", function () {
  //   numOutput.classList.remove(`hiddenCalo`);
  calo.textContent = material.calories;
  carb.textContent = material.carb;
  fat.textContent = material.fat;
  protein.textContent = material.protein;
  numOutput.forEach((element) => {
    element.classList.remove("hiddenCalo");
    console.log(element);
  });
});
