import { FilmsResponse, NodeFilmsResponse } from "./filmSlice";

export const fetchAllFilms = async (): Promise<FilmsResponse> => {
  const response = await fetch("https://swapi-node.now.sh/api/films", {
    method: "GET",
    headers: { "Content-Type": "application/json" }
  });
  const nodeResult: NodeFilmsResponse = await response.json();
  const result: FilmsResponse = {...nodeResult, results: nodeResult.results.map(result => result.fields)};

  return result;
};
