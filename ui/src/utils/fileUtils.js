import {importDataApi} from "../api/importDataApi";

export function uploadFile(file, dispatch) {
    handleReadingFile(file, (data, file) => {
        const binaryString = btoa(data)
        dispatch(importDataApi(
            {"content": binaryString}
        ))
    })
}

function handleReadingFile(data, callback) {
    const reader = new FileReader()
    if (data) {
        reader.onload = readerEvent => {
            const dataToLoad = readerEvent ? readerEvent.target.result : reader.content
            callback(dataToLoad, data)
        }
        reader.readAsBinaryString(data)
    }
}