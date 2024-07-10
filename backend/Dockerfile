FROM amazoncorretto:21-alpine

# Install necessary tools
RUN apk add --no-cache python3 py3-pip curl netcat-openbsd

# Create a virtual environment and install AWS CLI
RUN python3 -m venv /opt/venv
ENV PATH="/opt/venv/bin:$PATH"
RUN pip3 install --upgrade pip && \
    pip3 install awscli

# Install Gradle
ENV GRADLE_VERSION=8.5
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp && \
    unzip -d /opt/gradle /tmp/gradle-${GRADLE_VERSION}-bin.zip && \
    rm /tmp/gradle-${GRADLE_VERSION}-bin.zip

ENV PATH=${PATH}:/opt/gradle/gradle-${GRADLE_VERSION}/bin

WORKDIR /app

COPY . .
COPY deploy.sh /deploy.sh
RUN chmod +x /deploy.sh

CMD ["/bin/sh", "-c", "/deploy.sh && tail -f /dev/null"]