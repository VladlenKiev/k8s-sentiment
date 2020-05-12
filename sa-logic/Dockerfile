FROM python:3.6.6-alpine
RUN mkdir /app
ADD /sa/requirements.txt /sa/requirements.txt
COPY sa /app
WORKDIR /app
RUN apk add --no-cache musl-dev
RUN apk add --no-cache gcc

RUN pip3 install -r requirements.txt
RUN python3 -m textblob.download_corpora
EXPOSE 5000
ENTRYPOINT ["python3"]
CMD ["sentiment_analysis.py"]
