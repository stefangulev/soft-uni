const { chromium } = require('playwright-chromium');
const { expect } = require('chai');
let browser, page; // Declare reusable variables
describe('E2E tests', function() {
    this.timeout(6000);
  before(async () => { browser = await chromium.launch()});
  after(async () => { await browser.close() });
  beforeEach(async () => { page = await browser.newPage() });
  afterEach(async () => { await page.close() }); 

  it('test content', async () => {
     await page.goto('http://localhost:5500/01. Accordion');
     const content = await page.$$eval('.accordion .head span', (spans) => spans.map(s => s.textContent));
     expect(content).to.contains('Scalable Vector Graphics');
     expect(content).to.contains('Open standard');
     expect(content).to.contains('Unix');
     expect(content).to.contains('ALGOL');
})

it ('test click More', async() => {
  await page.goto('http://localhost:5500/01. Accordion');
  await page.click('text=MORE');
  await page.waitForSelector('.extra p');
  const visible = await page.isVisible('.extra p');
  expect(visible).to.be.true;
  const content = await page.textContent('.extra p');
  expect(content).to.contains('Scalable Vector Graphics (SVG) is an Extensible Markup Language (XML)-based vector image format for two-dimensional graphics with support for interactivity and animation. The SVG specification is an open standard developed by the World Wide Web Consortium (W3C) since 1999.');
  const button = await page.textContent('.button');
  expect(button).to.contain("Less");
})
it ('test click Less', async() => {
  await page.goto('http://localhost:5500/01. Accordion');
  await page.click('#main>.accordion:first-child >> text=More');
  const visible = await page.isVisible('.extra p');
  expect(visible).to.be.true;
  await page.waitForSelector('#main>.accordion:first-child >> .extra p');
  await page.click('#main>.accordion:first-child >> text=Less');
  button = await page.textContent('.button');
  expect(button).to.contain("More");
  const invisible = await page.isVisible('.extra p');
  expect(invisible).to.be.false;
  
})
});


