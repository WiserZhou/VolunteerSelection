async function populate() {

    const requestURL = 'http://localhost:719/hit_student/getInfo/zhaoyi';
    const request = new Request(requestURL);

    const response = await fetch(request);
    const superHeroes = await response.json();
    console.log(superHeroes);

}
