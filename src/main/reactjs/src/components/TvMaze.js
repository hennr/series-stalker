// @flow

import TvMaze from "tv-maze";

const tvMazeClient = TvMaze.createClient();

export default function client(query: string, callback: Function) {
    tvMazeClient.search(query, function (err, shows) {
        callback(shows);
    });
}
