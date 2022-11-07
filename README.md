# Semantic Release Proposal for Java Libraries

## Sonatype OSSRH (OSS Repository Hosting)

Create a ticket with Sonatype: Sonatype uses JIRA to manage requests.

1. Create your JIRA account - https://issues.sonatype.org/secure/Signup!default.jspa
2. Create a New Project ticket - https://issues.sonatype.org/secure/CreateIssue.jspa?pid=10134&issuetype=21

## Signing Java Artifacts

1. Generate public/private key with gpg commands

```shell
gpg --full-generate-key
gpg --keyring signgithubjars.gpg --export-secret-keys > ~/.gnupg/signgithubjars.gpg
```

## Publish Java Artifacts

1. Publish artifacts locally to Maven:
```shell
./gradlew
    clean
    publishMavenPublicationToLocalRepository
    -console=verbose
    -Psigning.keyId=XXXXXXXX
    -Psigning.password=AAAAAAA
    -Psigning.secretKeyRingFile=/Users/dsusanibar/.gnupg/signgithubjars.gpg
```

2. Publish artifacts to Sonatype:
```shell
./gradlew \
    clean \
    publishToSonatype \
    -Psigning.keyId=XXXXXXXX \
    -Psigning.password=AAAAAAA \
    -Psigning.secretKeyRingFile=/Users/dsusanibar/.gnupg/signgithubjars.gpg \
    -Psonatype.user=XXXXXXXX \
    -Psonatype.password=AAAAAAA \
    -PGITHUB_ACTOR=david
```

## Semantic-release Configuration


## Test release candidate changes

```shell
main < next < next-major < beta < alpha

Test: Change as a maintenance feature: feat: feature 001
Test: Change as a maintenance feature + break: feat: feature 001 !BREAKING CHANGE
Test: Change as a maintenance feature: feat: feature 002
Test: Main: Change as a maintenance feature: feat: feature 003 with next branch just created
OK: From: v1.1.0 To: v1.2.0
Test: Main: Change as a maintenance feature: feat: feature 004 with next-major branch just created

```
