import TvMaze from "tv-maze";

const tvMazeClient = TvMaze.createClient();

export default function client(query, callback) {
    tvMazeClient.search(query, function (err, shows) {
        callback(shows);
    });
}
