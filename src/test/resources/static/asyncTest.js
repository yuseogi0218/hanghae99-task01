// k6 Test : https://k6.io/
import http from "k6/http" // http test
import { sleep } from "k6" // sleep 기능 사용 시 추가
import { FormData } from 'https://jslib.k6.io/formdata/0.0.2/index.js';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';
import {encode} from 'https://raw.githubusercontent.com/mathiasbynens/utf8.js/refs/heads/master/utf8.js'
export let options = {
    vus: 10,          // 1,000명의 가상 유저
    duration: '10s'   // 테스트 진행 시간 1분
};

export default function () {
    let url = "http://localhost:8080/products/1/reviews";

    // http.get(url);

    const fd = new FormData();
    const reviewJSON = JSON.stringify({
        userId : randomIntBetween(16, 2**64),
        score : randomIntBetween(1, 5),
        content : '리뷰 내용',
    })

    fd.append('review', {
        data: encode(reviewJSON),
        content_type: 'application/json;'
    });

    http.post(url, fd.body(), {
        headers: { 'Content-Type': 'multipart/form-data; boundary=' + fd.boundary },
    });

    sleep(1);
}