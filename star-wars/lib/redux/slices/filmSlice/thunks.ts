/* Instruments */
import { createAppAsyncThunk } from "@/lib/redux/createAppAsyncThunk";
import { fetchAllFilms } from "./fetchAllFilms";
import { fetchFilm } from "./fetchFilm";

// The function below is called a thunk and allows us to perform async logic. It
// can be dispatched like a regular action: `dispatch(incrementAsync(10))`. This
// will call the thunk with the `dispatch` function as the first argument. Async
// code can then be executed and other actions can be dispatched. Thunks are
// typically used to make async requests.
export const getAllFilms = createAppAsyncThunk(
  "film/fetchAllFilms",
  async () => {
    const response = await fetchAllFilms();

    // The value we return becomes the `fulfilled` action payload
    return response.results;
  },
);

export const getFilm = createAppAsyncThunk(
  "film/fetchFilm",
  async (path: string) => {
    const response = await fetchFilm(path);

    // The value we return becomes the `fulfilled` action payload
    return response;
  },
);