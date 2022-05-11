const {app, BrowserWindow } = require("electron");
const path = require('path')

const loadNewWindow = () => {
    const application = new BrowserWindow({
        width: 500,
        heigh: 500,
        webPreferences: {
            preload: path.join(__dirname, '/file/preload/main.js')
        }
    });
    application.loadFile("./file/main.html");
}

app.whenReady().then(() => {
   loadNewWindow();
});

app.on('window-all-closed', function () {
   if (process.platform !== "darwin") app.quit();
});