const num1Element = document.getElementById("num1") as HTMLInputElement;
const num2Element = document.getElementById("num2") as HTMLInputElement;
const buttonElement = document.querySelector("button")!;

const numResults: number[] = [];
const stringResults: String[] = [];

type numOrString = number | string;

interface resultObj {
  val: number;
  timestamp: Date;
}

function add(num1: number | string, num2: number | string) {
  if (typeof num1 === "number" && typeof num2 === "number") {
    return num1 + num2;
  } else if (typeof num1 === "string" && typeof num2 === "string") {
    return num1 + " " + num2;
  }
  return +num1 + +num2;
}

console.log(add(1, 6));

function printResult(resultObj: { val: number; timestamp: Date }) {}

buttonElement.addEventListener("click", () => {
  const val1 = num1Element.value;
  const val2 = num2Element.value;
  const result = add(+val1, +val2);
  console.log(result);
  numResults.push(result as number);
  const stringResult = add(val1, val2);
  console.log(stringResult);
  stringResults.push(stringResult as string);
  printResult({ val: result as number, timestamp: new Date() });
  console.log(numResults, stringResults);
});
// console.log(add("1", "6"));
