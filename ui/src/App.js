import './App.css';
import {uploadFile} from "./utils/fileUtils";
import {useDispatch, useSelector} from "react-redux";
import ShipmentsTable from "./components/ShipmentsTable";

const _ = require('lodash');

function App() {

    const shipments = useSelector(state => state.shipmentsReducer.shipments);
    const dispatch = useDispatch();

    const onFileUpload = (file) => {
        uploadFile(file, dispatch)
    }

    return (
        <div className="App">
            <input type="file" data-show-upload="true" accept="text/csv"
                   onChange={e => onFileUpload(e.target.files[0])}/>
            {!_.isEmpty(shipments) && <ShipmentsTable data={shipments}/>}
        </div>
    );
}

export default App;
