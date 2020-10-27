import axios from "axios";

export default function client(query: string, callback: Function) {
    axios.get("https://api.tvmaze.com/search/shows?q=" + query)
        .then((response) => {
            callback(response.data);
        });
}
