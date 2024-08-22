import "../CSS/SearchResultsList.css";
import { SearchResult } from "./SearchResult";

export const SearchResultsList = ({results, getFoodLogs} ) => {
    return (
        <div className="results-list">
            {
                results.map((result, id) => {
                    return <SearchResult result={result} key={id} getFoodLogs={getFoodLogs}/>;
                })
            }
        </div>
    );
};