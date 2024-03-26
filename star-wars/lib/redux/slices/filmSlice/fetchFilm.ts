import { NodeCharactersResponse, FilmResponse, NodeFilmResponse, NodePlanetsResponse, NodeSpeciesResponse, NodeStarshipsResponse, NodeVehiclesResponse } from "./filmSlice";

export const fetchFilm = async (path: string): Promise<FilmResponse> => {
  const BASE_URL = "https://swapi-node.now.sh";

  const response = await fetch(`${BASE_URL}/api${path}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" }
  });
  const nodeResult: NodeFilmResponse = await response.json();
  const result: FilmResponse = nodeResult.fields;


  const resCharacters = result.characters.map((url) =>
    fetch(`${BASE_URL}${url}`)
      .then((data) => data.json())
      .then((c: NodeCharactersResponse) => (c.fields.name))
  );
  const resPlanets = result.planets.map((url) =>
    fetch(`${BASE_URL}${url}`)
      .then((data) => data.json())
      .then((c: NodePlanetsResponse) => c.fields.name)
  );
  const resSpecies = result.species.map((url) =>
    fetch(`${BASE_URL}${url}`)
      .then((data) => data.json())
      .then((c: NodeSpeciesResponse) => c.fields.name)
  );
  const resStarships = result.starships.map((url) =>
    fetch(`${BASE_URL}${url}`)
      .then((data) => data.json())
      .then((c: NodeStarshipsResponse) => c.fields.starship_class)
  );
  const resVehicles = result.vehicles.map((url) =>
    fetch(`${BASE_URL}${url}`)
      .then((data) => data.json())
      .then((c: NodeVehiclesResponse) => c.fields.name)
  );
  const characters = await Promise.all(resCharacters);
  const planets = await Promise.all(resPlanets);
  const species = await Promise.all(resSpecies);
  const starships = await Promise.all(resStarships);
  const vehicles = await Promise.all(resVehicles);
  const data = {... result, characters, planets, species, starships, vehicles}

  return data;
};
