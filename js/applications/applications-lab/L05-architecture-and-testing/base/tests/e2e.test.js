//@ts-check
const { chromium } = require('playwright-chromium');
const { expect } = require('chai');

const mockData = require('./mock-data.json');
function json(data) {
    return {
        status: 200,
        headers: {
            'Access-Control-Allow-Origin': "*",
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }
}
let browser;
let context;
let page;

describe('E2E tests', function () {
    this.timeout(6000);

    before(async () => {
        // browser = await chromium.launch({ headless: false, slowMo: 6000 });
        browser = await chromium.launch();
    });

    after(async () => {
        await browser.close();
    });

    beforeEach(async () => {
        context = await browser.newContext();

        // block intensive resources and external calls (page routes take precedence)
        await context.route('**/*.{png,jpg,jpeg}', route => route.abort());
        await context.route(url => {
            return url.hostname != 'localhost';
        }, route => route.abort());

        page = await context.newPage();
    });

    afterEach(async () => {
        await page.close();
        await context.close();
    });

    describe('loads and displays', async () => {
        it('check if we work', async () => {
            await page.route('**/data/recipes*', (request) => request.fulfill(json(mockData.list)))
            await page.goto('http://localhost:5500/base/');
            await page.waitForSelector('article');
            const content = await page.$$eval('h2',content => content.map(a => a.textContent));
            expect(content[0]).to.contains('Easy Lasagna')
            expect(content[1]).to.contains('Grilled Duck Fillet')
            expect(content[2]).to.contains('Roast Trout')
        })
        
    })
    describe("Authentification", async () => {
        it('check register', async() => {
            await page.route('**/users/register', route => route.fulfill(json({_id: '001', email ,accessToken: 'AAAA'})));
            const email = 'ivan@abv.bg';
            const password = '123';
            const rePass = '123';

            await page.goto('http://localhost:5500/base/');
            await page.click('text=Register');

            await page.waitForSelector('form');

            await page.fill('[name="email"]', email);
            await page.fill('[name="password"]', password);
            await page.fill('[name="rePass"]', rePass);

         const [request] = await Promise.all([
            page.waitForRequest(request => request.url().includes('/users/register') && request.method() == "POST"),
            page.click('[type="submit"]')
           ]) 

           const postData = JSON.parse(request.postData());
            expect(postData.email).to.equal(email);
            expect(postData.password).to.equal(password);
        })
    })
    it.only('check login', async() => {
        await page.route('**/users/login', route => route.fulfill(json({_id: '001', email ,accessToken: 'AAAA'})));
        const email = 'ivan@abv.bg';
        const password = '123';

        await page.goto('http://localhost:5500/base/');
        await page.click('text=Login');

        await page.waitForSelector('form');

        await page.fill('[name="email"]', email);
        await page.fill('[name="password"]', password);
    
     const [request] = await Promise.all([
        page.waitForRequest(request => request.url().includes('/users/login') && request.method() == "POST"),
        page.click('[type="submit"]')
       ]) 

       const postData = JSON.parse(request.postData());
        expect(postData.email).to.equal(email);
        expect(postData.password).to.equal(password);
    })
})


