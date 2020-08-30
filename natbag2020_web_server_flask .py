
import subprocess

from flask import request, Flask

app = Flask("my_app1")

@app.route("/departures")
def departures():
    return subprocess.check_output(["java","-classpath",
                                    "C:/Users/Luba/eclipse-workspace/Natbag2020/bin","BrowserMain",
                                    request.args.get('outformat'),
                                    "departures",
                                    request.args.get('country'),
                                    request.args.get('city'),
                                    request.args.get('airport'),
                                    request.args.get('company'),
                                    request.args.get('day1'),
                                    request.args.get('month1'),
                                    request.args.get('year1'),
                                    request.args.get('day2'),
                                    request.args.get('month2'),
                                    request.args.get('year2')])
                           #         request.args.get('sunday'),
                           #         request.args.get('monday'),
                           #         request.args.get('tuesday'),
                           #         request.args.get('wednesday'),
                           #         request.args.get('thursday'),
                           #         request.args.get('friday'),
                           #         request.args.get('saturday')])
@app.route("/arrivals")
def arrivals():
    return subprocess.check_output(["java","-classpath",
                                    "C:/Users/Luba/eclipse-workspace/Natbag2020/bin","BrowserMain",
                                    request.args.get('outformat'),
                                    "arrivals",
                                    request.args.get('country'),
                                    request.args.get('city'),
                                    request.args.get('airport'),
                                    request.args.get('company'),
                                    request.args.get('day1'),
                                    request.args.get('month1'),
                                    request.args.get('year1'),
                                    request.args.get('day2'),
                                    request.args.get('month2'),
                                    request.args.get('year2')])
                         #          request.args.get('sunday'),
                         #          request.args.get('monday'),
                         #          request.args.get('tuesday'),
                         #          request.args.get('wednesday'),
                         #          request.args.get('thursday'),
                         #          request.args.get('friday')
                         #          request.args.get('saturday')])

app.run(port=8000, host="0.0.0.0")