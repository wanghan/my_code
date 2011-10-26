#include <stdio.h>
#include <netdb.h>

/**
 *	s is domain name or dotted-decimal
 */
struct hostent * getHostEnt(char * s) {

	struct in_addr addr;
	struct hostent *hostp;

	if (inet_aton(s, &addr) != 0) {
		hostp = gethostbyaddr((const char *) &addr, sizeof(addr), AF_INET);
	} else {
		hostp = gethostbyname(s);
	}
	return hostp;
}

void printHostent(struct hostent * hostp) {
	printf("official name : %s\n", hostp->h_name);
	char **pp;
	struct in_addr addr;
	for (pp = hostp->h_aliases; *pp != NULL; ++pp) {
		printf("alias : %s\n", *pp);
	}
	for (pp = hostp->h_addr_list; *pp != NULL; ++pp) {
		addr.s_addr = ((struct in_addr*) *pp)->s_addr;
		printf("address : %s\n", inet_ntoa(addr));
	}
}

int main() {
	struct hostent * host1 = getHostEnt("google.com");
	printHostent(host1);
}
