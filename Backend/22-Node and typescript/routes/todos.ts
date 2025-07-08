import { Router } from "express";
import { Todo } from "../models/todo";
const router = Router();

const todos: Todo[] = [];

router.get("/", (req, res, next) => {
  res.status(200).json({ todos: todos });
});

router.post("/", (req, res, next) => {
  const newTodo: Todo = {
    id: new Date().toISOString(),
    text: "fda",
  };
  todos.push(newTodo);
});

export default router;
