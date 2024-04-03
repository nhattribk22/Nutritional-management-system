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
const resultBox = document.querySelector(`.result-box`);
const inputBox = document.querySelector(`#input-box`);
var final;
function selectInput(list) {
        final = list.textContent;
        inputBox.value = list.innerHTML;
        resultBox.innerHTML = ``;
}



function fetchDataFromAPI(url) {
  return fetch(url)
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      // Search raw
      let jsonData = data;
      const foodNames = jsonData.map(item => Object.keys(item)[0]);
      console.log(foodNames);
      
      // console.log(farr);
      const strawberryFat = jsonData.find(item => Object.keys(item)[0] === "Strawberry")["Strawberry"]["fat"];
      
      console.log(strawberryFat); // Output: 0.3
      let keyWords = foodNames;
      inputBox.onkeyup = function () {
        
        let result = [];
        let input = inputBox.value;
        if (input.length) {
          result = keyWords.filter((kWord) => {
            return kWord.toLowerCase().includes(input.toLowerCase());
          });
          // console.log(result, typeof result);
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
        fat: 30,
        protein: 230,
      };
      
      searchBtn.addEventListener("click", function () {
        // console.log(input);
        final = final.slice(0, -1);
        final = final.substring(1);

        const customVariable = jsonData.find(item => Object.keys(item)[0] === final)[final];
        console.log(customVariable);
        calo.textContent = customVariable.calories;
        carb.textContent = customVariable.carb;
        fat.textContent = customVariable.fat;

        protein.textContent = customVariable.protein;
        numOutput.forEach((element) => {
          element.classList.remove("hiddenCalo");
          console.log(element);
        });
      });

      // Return data for further processing if necessary
      return data;
    })
    .catch(error => {
      console.error('There was a problem with the fetch operation:', error);
    });
}


fetchDataFromAPI('./Details.json')
.then(data => {
  console.log(data);
});
